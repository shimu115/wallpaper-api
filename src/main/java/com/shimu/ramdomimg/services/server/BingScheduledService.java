package com.shimu.ramdomimg.services.server;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shimu.ramdomimg.enums.ApiContains;
import com.shimu.ramdomimg.enums.BingJsonI18nEnum;
import com.shimu.ramdomimg.exception.RandomImgException;
import com.shimu.ramdomimg.model.po.BingWallpaperPO;
import com.shimu.ramdomimg.model.response.GitHubJsonResponse;
import com.shimu.ramdomimg.model.response.GitHubJsonResult;
import com.shimu.ramdomimg.repository.BingWallpaperRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class BingScheduledService {

    @Autowired
    private BingWallpaperRepository repository;

    @Autowired
    private Executor initExecutor;

    private volatile boolean initialized = false; // 标记是否完成初始化

    /**
     * 项目启动时立即拉取一次数据
     */
    @PostConstruct
    public void init() {
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
    @Scheduled(fixedRate = 3600_000) // 每小时刷新一次
    public void refreshAllLanguages() {
        for (BingJsonI18nEnum langEnum : BingJsonI18nEnum.values()) {
            try {
                String url = getBingJsonUrl(langEnum.name());
                String resp = fetchFromGitHub(url);

                GitHubJsonResult<List<GitHubJsonResponse>> gitHubJsonResult =
                        JSONObject.parseObject(resp, new TypeReference<GitHubJsonResult<List<GitHubJsonResponse>>>() {});

                if (gitHubJsonResult == null || gitHubJsonResult.getData() == null) {
                    log.warn("⚠️ {} 数据为空", langEnum.name());
                    continue;
                }

                // 保存到 SQLite
                repository.deleteByI18nKey(langEnum.name()); // 删除该语言的旧数据
                gitHubJsonResult.getData().forEach(item -> {
                    BingWallpaperPO entity = new BingWallpaperPO();
                    entity.setUrl(item.getUrl());
                    entity.setCopyright(item.getCopyright());
                    entity.setCopyrightLink(item.getCopyrightLink());
                    entity.setHsh(item.getHsh());
                    entity.setI18nKey(langEnum.getKey());
                    entity.setDateTime(item.getDateTime());
                    entity.setCreatedTime(item.getCreatedTime());
                    entity.setTitle(item.getTitle());
                    repository.save(entity);
                });

                log.info("{} 数据刷新完成，存储 {} 条", langEnum.name(), gitHubJsonResult.getData().size());

            } catch (Exception e) {
                log.error("拉取 {} 失败: {}", langEnum.name(), e.getMessage());
            }
        }
        initialized = true;
    }

    /**
     * 从 GitHub 拉取数据（Java 8 兼容写法）
     */
    private String fetchFromGitHub(String url) throws IOException {
        try (InputStream in = new URL(url).openStream();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    private String getBingJsonUrl (String i18nKey) {
        BingJsonI18nEnum enumResult = EnumUtils.getEnum(BingJsonI18nEnum.class, i18nKey);
        if (enumResult == null) {
            throw new RandomImgException("无效参数");
        }
        return StringUtils.replace(ApiContains.BING_GITHUB_JSON, "{i18n_key}", enumResult.getKey());
    }

    public boolean isInitialized() {
        return initialized;
    }
}
