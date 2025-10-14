package com.shimu.wallpaper.api.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.enums.ApiContains;
import com.shimu.wallpaper.api.enums.BingJsonI18nEnum;
import com.shimu.wallpaper.api.exception.WallpaperApiException;
import com.shimu.wallpaper.api.model.response.BingResponse;
import com.shimu.wallpaper.api.services.BingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片流处理工具类
 */
@Slf4j
@Component
public class StreamResponseUtils {

    @Autowired
    private BingService bingService;

    private static StreamResponseUtils streamResponseUtils;

    @PostConstruct
    public void init() {
        streamResponseUtils = this;
    }

    public static void streamImage(HttpServletResponse response, String imageUrl, String userAgent, String i18nKey, Integer width, Integer height) {
        BingService service = streamResponseUtils.bingService;
        log.info("请求图片 url：{}", imageUrl);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(imageUrl).openConnection();
            int status = conn.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK && !StringUtils.equals(imageUrl, getTodayWallpaperUrl(i18nKey))) {
                log.error("对应链接可能已失效，默认展示今日壁纸，请求失败 url：{}", imageUrl);
                service.getTodayWallpaper(response, userAgent, i18nKey, width, height);
            } else if (status != HttpURLConnection.HTTP_OK && StringUtils.equals(imageUrl, getTodayWallpaperUrl(i18nKey))) {
                throw new WallpaperApiException("请求失败，对应链接可能已失效，请刷新重试！", 50002);
            }
        } catch (IOException e) {
            throw new WallpaperApiException(e);
        }

        String contentType = conn.getContentType();
        int contentLength = conn.getContentLength();

        if (contentType != null) response.setContentType(contentType);
        if (contentLength > 0) response.setContentLength(contentLength);

        long totalBytes = 0; // 累加字节数
        try (InputStream in = conn.getInputStream();
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                totalBytes += len;
            }
            out.flush();
            double streamSizeMB = totalBytes / 1024.0 / 1024.0;
            log.info("图片 stream 大小: {} MB", String.format("%.2f", streamSizeMB));
        } catch (ClientAbortException e) {
            log.warn("传输失败: {}, 失败原因: {}", imageUrl, e.getMessage());
            throw new WallpaperApiException("客户端在传输过程中断开连接，图片未传输完毕: " + e.getMessage() + "已传输大小: " + String.format("%.2f", totalBytes / 1024.0 / 1024.0) + "MB", 50003);
        } catch (IOException e) {
            throw new WallpaperApiException(e);
        } finally {
            conn.disconnect();
        }
    }

    public static String getTodayWallpaperUrl(String i18nKey) {
        if (StringUtils.isEmpty(i18nKey)) {
            i18nKey = BingJsonI18nEnum.zh_CN.name();
        } else {
            i18nKey = EnumUtils.getEnum(BingJsonI18nEnum.class, i18nKey).name();
        }
        // 1. 请求 Bing 官方 JSON 接口
        String bingApi = StringUtils.replace(ApiContains.BING_PHOTO_API, "{i18nKey}", i18nKey);
        BingResponse bing = JSON.parseObject(HttpUtil.get(bingApi), BingResponse.class);

        if (ObjectUtils.isEmpty(bing)) {
            throw new WallpaperApiException("请求失败，请稍后再试", 50001);
        }
        log.info("返回数据：{}", JSON.toJSONString(bing.getImages()));
        // 2. 拼接完整图片地址
        return "https://www.bing.com" + bing.getImages().get(0).getUrlBase() + "_{res}";
    }
}
