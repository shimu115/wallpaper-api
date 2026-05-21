package com.shimu.wallpaper.api.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.shimu.wallpaper.api.component.MirrorComponent;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 查看 url 是否可用
 */
@Component
@Slf4j
public class HttpUtils {

    private static Integer maxRetry;

    @Autowired
    private MirrorComponent mirrorComponent;

    @PostConstruct
    public void init() {
        Integer retryCount = mirrorComponent.getRetryCount();
        if (retryCount == null || retryCount > 10) {
            log.warn("retryCount 配置值为 {}，超出限制(最大10)，已重置为默认值 3", retryCount);
            retryCount = 3;
        }
        maxRetry = retryCount;
        log.info("HttpUtils 初始化完成, maxRetry: {}", maxRetry);
    }

    public static int getMaxRetry() {
        return maxRetry != null ? maxRetry : 3;
    }

    /**
     * GET 请求，带重试，全部失败返回 null
     */
    public static String httpGet(String url, int maxRetry) {
        for (int i = 0; i < maxRetry; i++) {
            try {
                String result = cn.hutool.http.HttpUtil.get(url);
                if (result != null && !result.isEmpty()) {
                    return result;
                }
                log.warn("GET {} 返回空结果, 第{}次重试", url, i + 1);
            } catch (Exception e) {
                log.warn("GET {} 失败: {}, 第{}次重试", url, e.getMessage(), i + 1);
            }
        }
        log.error("GET {} 重试{}次后全部失败", url, maxRetry);
        return null;
    }

    public static boolean isReachable(String url) {
        int retries = maxRetry != null ? maxRetry : 1;
        for (int i = 0; i < retries; i++) {
            try {
                HttpResponse response = HttpRequest.head(url)
                        .timeout(5000)
                        .execute();
                int status = response.getStatus();
                if (status >= 200 && status < 400) {
                    return true;
                }
                log.warn("mirror: {}, 状态码: {}, 第{}次重试", url, status, i + 1);
            } catch (Exception e) {
                log.warn("mirror: {} 请求异常, 第{}次重试", url, i + 1);
            }
        }
        log.error("mirror 请求失败：{}，已重试{}次，请检查 mirror.github 配置的镜像地址是否有误", url, retries);
        return false;
    }
}
