package com.shimu.wallpaper.api.services.server;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shimu.wallpaper.api.component.MirrorResolver;
import com.shimu.wallpaper.api.enums.BingJsonI18nEnum;
import com.shimu.wallpaper.api.model.po.BingWallpaperPO;
import com.shimu.wallpaper.api.model.response.GitHubJsonResponse;
import com.shimu.wallpaper.api.model.response.GitHubJsonResult;
import com.shimu.wallpaper.api.repository.BingWallpaperRepository;
import com.shimu.wallpaper.api.utils.HttpUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BingScheduledService {

    @Autowired
    private BingWallpaperRepository repository;

    @Autowired
    private MirrorResolver mirrorResolver;

    @Autowired
    private Executor initExecutor;

    @Value("${task.wallpaper.enable:true}")
    private boolean enable;

    @Getter
    private volatile boolean initialized = false; // 标记是否完成初始化

    /**
     * 项目启动时立即拉取一次数据
     */
    @PostConstruct
    public void init() {
        if (!enable) {
            initialized = true;
            return;
        }
        long count = repository.count();
        if (count >= 1000) {
            initialized = true;
            log.info("数据库内有 {} 条数据，无需初始化", count);
            return;
        }
        initExecutor.execute(() -> {
            log.info("后台线程启动 Bing 壁纸数据初始化任务...");
            try {
                refreshAllLanguages();
                initialized = true;
                log.info("Bing 数据初始化完成");
            } catch (Exception e) {
                log.error("初始化 Bing 数据失败: {}", e.getMessage(), e);
            }
        });
    }

    /**
     * 定时任务：每隔 1 小时刷新一次数据
     */
    @Scheduled(cron = "${task.wallpaper.cron:0 0 * * * ?}") // 每小时执行一次
    @Transactional
    public void refreshAllLanguages() {
        if (!enable) {
            initialized = true;
            return;
        }
        log.info("<===================> 开始刷新数据 <===================>");
        for (BingJsonI18nEnum lang : BingJsonI18nEnum.values()) {
            try {
                refreshLanguage(lang);
            } catch (Exception e) {
                log.error("拉取 {} 失败: {}", lang.name(), e.getMessage(), e);
            }
        }
        log.info("刷新数据后，共有 {} 条数据", repository.count());
        initialized = true;
    }

    private void refreshLanguage(BingJsonI18nEnum lang) throws InterruptedException {
        log.info("开始刷新语言: {}", lang.getKey());
        int maxRetry = HttpUtils.getMaxRetry();

        // 先尝试用户配置的镜像
        List<String> configuredUrls = mirrorResolver.getConfiguredUrls();
        String resp = tryUrls(configuredUrls, lang, maxRetry, "尝试请求: {}");

        // 用户配置均不可用，兜底使用默认镜像
        if (resp == null) {
            List<String> defaultUrls = mirrorResolver.defaultMirror();
            if (!defaultUrls.isEmpty()) {
//                log.warn("配置的镜像均不可用，尝试使用默认镜像兜底...");
                resp = tryUrls(defaultUrls, lang, maxRetry, "尝试默认镜像: {}");
            }
        }

        if (resp == null) {
            log.error("所有镜像(含默认)均不可用，已重试 {} 次，语言: {}", maxRetry, lang.getKey());
            throw new RuntimeException("所有镜像均不可用，请求失败");
        }

        GitHubJsonResult<List<GitHubJsonResponse>> gitHubJsonResult =
                JSONObject.parseObject(resp, new TypeReference<GitHubJsonResult<List<GitHubJsonResponse>>>() {});

        List<BingWallpaperPO> exiting = repository.findByI18nKey(lang.getKey());

        Map<Integer, BingWallpaperPO> exitingMap = exiting.stream()
                .collect(Collectors.toMap(
                        BingWallpaperPO::getDataId,
                        e -> e,
                        (existing, replacement) -> existing  // 保留第一个
                ));

        if (gitHubJsonResult == null || gitHubJsonResult.getCode() != 200) {
            throw new RuntimeException("请求失败");
        }

        List<GitHubJsonResponse> data = gitHubJsonResult.getData();
        if (data == null || data.isEmpty()) {
            log.warn("语言 {} 没有数据", lang.getKey());
            return;
        }

        // 转换成实体
        List<BingWallpaperPO> entities = new ArrayList<>();
        for (GitHubJsonResponse item : data) {
            if (!exitingMap.containsKey(item.getId())) {
                // 排除以此域名访问的 url，此域名访问不通
                if (StringUtils.startsWith(item.getUrl(), "https://cdn.bimg.cc")) {
                    continue;
                }
                BingWallpaperPO entity = new BingWallpaperPO();
                entity.setId(UUID.randomUUID().toString()); // 避免锁库用 UUID
                entity.setDataId(item.getId());
                entity.setUrl(item.getUrl());
                entity.setCopyright(item.getCopyright());
                entity.setCopyrightLink(item.getCopyrightLink());
                entity.setHsh(item.getHsh());
                entity.setI18nKey(lang.getKey());
                entity.setDateTime(item.getDateTime());
                entity.setCreatedTime(item.getCreatedTime());
                entity.setTitle(item.getTitle());
                repository.save(entity);
                entities.add(entity);
            }
        }

        // 批量保存 + 重试机制
        int retry = 3;
        while ((retry = retry - 1) > 0) {
            try {
                repository.saveAll(entities); // 批量写入
                log.info("语言 {} 保存成功, 条数: {}", lang.getKey(), entities.size());
                break;
            } catch (Exception e) {
                if (e.getMessage() != null && e.getMessage().contains("database is locked")) {
                    log.warn("数据库被锁，重试中... 剩余次数: {}", retry);
                    Thread.sleep(500); // 等待再试
                } else {
                    throw e;
                }
            }
        }
    }

    private String tryUrls(List<String> urls, BingJsonI18nEnum lang, int maxRetry, String logFormat) {
        for (String url : urls) {
            String finalUrl = StringUtils.replace(url, "{i18n_key}", lang.getKey());
            log.info(logFormat, finalUrl);
            String resp = HttpUtils.httpGet(finalUrl, maxRetry);
            if (resp != null) {
                return resp;
            }
        }
        return null;
    }

}
