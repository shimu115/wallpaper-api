package com.shimu.wallpaper.api.utils;

import java.net.URI;

/**
 * URL 地址拼接与域名替换工具
 */
public class AddrUtil {

    /**
     * 替换域名：将原 URL 的 host 替换为 mirror 地址的 host
     */
    public static String replaceHost(String originalUrl, String mirrorUrl) {
        String mirrorWithProtocol = ensureProtocol(mirrorUrl);
        try {
            URI mirrorUri = new URI(mirrorWithProtocol);
            URI originalUri = new URI(originalUrl);
            return new URI(mirrorUri.getScheme(), mirrorUri.getHost(),
                    originalUri.getPath(), originalUri.getQuery(), originalUri.getFragment())
                    .toString();
        } catch (Exception e) {
            String host = mirrorWithProtocol.replaceFirst("^https?://", "").replaceFirst("/.*", "");
            String protocol = mirrorWithProtocol.startsWith("https") ? "https" : "http";
            String path = originalUrl.replaceFirst("^https?://[^/]+", "");
            String query = originalUrl.contains("?") ? originalUrl.substring(originalUrl.indexOf("?")) : "";
            return protocol + "://" + host + path + query;
        }
    }

    /**
     * 拼接 mirror 地址到原 URL 前
     *
     * @param originalUrl  原始完整 URL
     * @param mirrorUrl    mirror 地址
     * @param withProtocol true=保留原 URL 协议; false=去掉原 URL 协议
     */
    public static String concat(String originalUrl, String mirrorUrl, boolean withProtocol) {
        String mirror = mirrorUrl.endsWith("/") ? mirrorUrl.substring(0, mirrorUrl.length() - 1) : mirrorUrl;
        if (withProtocol) {
            return mirror + "/" + originalUrl;
        } else {
            String stripped = originalUrl.replaceFirst("^https?://", "");
            return mirror + "/" + stripped;
        }
    }

    /**
     * 确保 URL 有协议，没有则默认 https
     */
    private static String ensureProtocol(String url) {
        if (url == null || url.isEmpty()) {
            return url;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return "https://" + url;
        }
        return url;
    }
}