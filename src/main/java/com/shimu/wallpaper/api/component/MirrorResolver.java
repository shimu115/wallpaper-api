package com.shimu.wallpaper.api.component;

import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.enums.ApiContains;
import com.shimu.wallpaper.api.enums.MirrorMethod;
import com.shimu.wallpaper.api.model.MirrorCache;
import com.shimu.wallpaper.api.model.MirrorCacheItem;
import com.shimu.wallpaper.api.utils.AddrUtil;
import com.shimu.wallpaper.api.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 镜像地址解析器 — 启动时自动探测每个 mirror 的可用拼接方式，缓存到本地 JSON，
 * 运行时按优先级返回所有可用地址，配合重试与兜底。
 */
@Component
@Slf4j
public class MirrorResolver {

    @Autowired
    private MirrorComponent mirrorComponent;

    private static MirrorResolver instance;

    private MirrorCache cache;

    private static final String CACHE_FILE = "data/mirror.json";
    private static final String TEST_PATH = "/flow2000/bing-wallpaper-api/refs/heads/master/data/zh-CN_all.json";

    @PostConstruct
    public void init() {
        instance = this;
        loadOrBuildCache();
        setApiContainsUrl();
        log.info("MirrorResolver 初始化完成, 缓存 mirror 数量: {}",
                cache != null && cache.getMirror() != null ? cache.getMirror().size() : 0);
    }

    // ==================== 缓存加载 / 构建 ====================

    private void loadOrBuildCache() {
        List<String> configuredMirrors = mirrorComponent.getGithub();

        // 尝试从 JSON 文件加载
        File cacheFile = new File(CACHE_FILE);
        if (cacheFile.exists()) {
            try {
                String json = new String(Files.readAllBytes(Paths.get(CACHE_FILE)), StandardCharsets.UTF_8);
                MirrorCache loaded = JSON.parseObject(json, MirrorCache.class);
                if (loaded != null
                        && loaded.getSourceMirrors() != null
                        && loaded.getMirror() != null
                        && !loaded.getMirror().isEmpty()
                        && loaded.getSourceMirrors().equals(configuredMirrors)) {
                    this.cache = loaded;
                    log.info("从 {} 加载 mirror 缓存成功", CACHE_FILE);
                    return;
                }
                log.info("mirror 配置已变更或缓存为空，重新探测");
            } catch (IOException e) {
                log.warn("读取 {} 失败: {}，重新探测", CACHE_FILE, e.getMessage());
            }
        }

        // 缓存不存在或已过期 → 重新探测
        this.cache = buildCache(configuredMirrors);
        saveCacheToFile();
    }

    private MirrorCache buildCache(List<String> mirrors) {
        LinkedHashMap<String, MirrorCacheItem> map = new LinkedHashMap<>();

        if (mirrors != null && !mirrors.isEmpty()) {
            int index = 0;
            String testFullUrl = ApiContains.BING_GITHUB_JSON_DEFAULT_HOST + TEST_PATH;

            for (String mirror : mirrors) {
                if (!HttpUtils.isReachable(mirror)) {
                    log.warn("mirror 不可用，跳过: {}", mirror);
                    continue;
                }

                MirrorMethod bestMethod = probeMethod(mirror, testFullUrl);
                if (bestMethod != null) {
                    String fullUrlWithPlaceholder = buildFullUrl(mirror, bestMethod);
                    MirrorCacheItem item = new MirrorCacheItem();
                    item.setUrl(fullUrlWithPlaceholder);
                    item.setMethod(bestMethod.getValue());
                    map.put("github_mirror_" + index, item);
                    index++;
                    log.info("mirror 可用: {} (方式: {})", mirror, bestMethod.getValue());
                } else {
                    log.warn("mirror 可连通但所有拼接方式均不匹配，跳过: {}", mirror);
                }
            }
        }

        MirrorCache result = new MirrorCache();
        result.setSourceMirrors(mirrors);
        result.setMirror(map);
        return result;
    }

    /**
     * 对单个 mirror 依次尝试三种方式，返回第一个可用的方式
     */
    private MirrorMethod probeMethod(String mirror, String testFullUrl) {
        for (MirrorMethod method : MirrorMethod.values()) {
            String testUrl = applyMethod(testFullUrl, mirror, method);
            log.debug("探测: mirror={}, method={}, url={}", mirror, method.getValue(), testUrl);
            if (HttpUtils.isReachable(testUrl)) {
                return method;
            }
        }
        return null;
    }

    private String applyMethod(String originalUrl, String mirror, MirrorMethod method) {
        switch (method) {
            case REPLACE:
                return AddrUtil.replaceHost(originalUrl, mirror);
            case PREFIX_HTTP:
                return AddrUtil.concat(originalUrl, mirror, true);
            case PREFIX:
                return AddrUtil.concat(originalUrl, mirror, false);
            default:
                return originalUrl;
        }
    }

    private String buildFullUrl(String mirror, MirrorMethod method) {
        String defaultFull = ApiContains.BING_GITHUB_JSON_DEFAULT_HOST + ApiContains.BING_GITHUB_JSON_PATH;
        return applyMethod(defaultFull, mirror, method);
    }

    private void saveCacheToFile() {
        try {
            String json = JSON.toJSONString(cache, true);
            Files.write(Paths.get(CACHE_FILE), json.getBytes(StandardCharsets.UTF_8));
            log.info("mirror 缓存已写入 {}", CACHE_FILE);
        } catch (IOException e) {
            log.error("保存 {} 失败: {}", CACHE_FILE, e.getMessage());
        }
    }

    // ==================== 运行时 API ====================

    /**
     * 返回所有可用 mirror 拼接后的完整 URL（含 {i18n_key} 占位符），
     * 列表末尾是默认地址作为最终兜底。
     */
    public List<String> getUrls() {
        List<String> urls = new ArrayList<>();
        if (cache != null && cache.getMirror() != null) {
            for (MirrorCacheItem item : cache.getMirror().values()) {
                urls.add(item.getUrl());
            }
        }
        // 兜底：默认地址
        urls.add(ApiContains.BING_GITHUB_JSON_DEFAULT_HOST + ApiContains.BING_GITHUB_JSON_PATH);
        return urls;
    }

    /**
     * 获取缓存中第一个可用 URL（含占位符），若无缓存则返回默认地址。
     */
    public String getFirstUrl() {
        if (cache != null && cache.getMirror() != null && !cache.getMirror().isEmpty()) {
            return cache.getMirror().values().iterator().next().getUrl();
        }
        return ApiContains.BING_GITHUB_JSON_DEFAULT_HOST + ApiContains.BING_GITHUB_JSON_PATH;
    }

    private void setApiContainsUrl() {
        ApiContains.BING_GITHUB_JSON = getFirstUrl();
        log.info("ApiContains.BING_GITHUB_JSON 已设置为: {}", ApiContains.BING_GITHUB_JSON);
    }
}