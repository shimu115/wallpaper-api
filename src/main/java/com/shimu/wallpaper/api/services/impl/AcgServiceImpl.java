package com.shimu.wallpaper.api.services.impl;

import cn.hutool.http.HttpUtil;
import com.shimu.wallpaper.api.enums.ApiContains;
import com.shimu.wallpaper.api.exception.WallpaperApiException;
import com.shimu.wallpaper.api.services.AcgService;
import com.shimu.wallpaper.api.utils.StreamResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class AcgServiceImpl implements AcgService {
    @Override
    public void random(HttpServletResponse response, HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
//        String url = HttpUtil.get(ApiContains.ACG_WALLPAPER_API);
//        StreamResponseUtils.streamImage(response, url);
        log.info("userAgent:{}", userAgent);
        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(ApiContains.ACG_WALLPAPER_API);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (userAgent != null) {
                conn.setRequestProperty("User-Agent", userAgent); // 转发 UA
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            throw new WallpaperApiException("请求失败", 51000);
        } finally {
            if (conn != null) conn.disconnect();
        }
        String url = result.toString().trim();
        log.info("图片地址: {}", url);
        StreamResponseUtils.streamImage(response, url);
    }
}
