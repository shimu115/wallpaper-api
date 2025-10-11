package com.shimu.wallpaper.api.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

/**
 * 查看 url 是否可用
 */
public class HttpUtils {
    public static boolean isReachable(String url) {
        try {
            HttpResponse response = HttpRequest.head(url)
                    .timeout(5000)
                    .execute();

            int status = response.getStatus();
            return status >= 200 && status < 400;
        } catch (Exception e) {
            return false;
        }
    }
}
