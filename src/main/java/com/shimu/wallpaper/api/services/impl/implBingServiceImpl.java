package com.shimu.wallpaper.api.services.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.enums.ApiContains;
import com.shimu.wallpaper.api.enums.BingJsonI18nEnum;
import com.shimu.wallpaper.api.exception.RandomImgException;
import com.shimu.wallpaper.api.model.po.BingWallpaperPO;
import com.shimu.wallpaper.api.model.response.BingResponse;
import com.shimu.wallpaper.api.repository.BingWallpaperRepository;
import com.shimu.wallpaper.api.services.BingService;
import com.shimu.wallpaper.api.services.server.BingScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
public class implBingServiceImpl implements BingService {

    @Autowired
    private BingScheduledService bingScheduledService;

    @Autowired
    private BingWallpaperRepository repository;

    @Override
    public void getTodayWallpaper(HttpServletResponse response) {
        streamImage(response, getTodayWallpaperUrl());
    }

    private String getTodayWallpaperUrl() {
        // 1. 请求 Bing 官方 JSON 接口
        String bingApi = ApiContains.BING_PHOTO_API;
        RestTemplate restTemplate = new RestTemplate();
        BingResponse bing = restTemplate.getForObject(bingApi, BingResponse.class);

        // 2. 拼接完整图片地址
        assert bing != null;
        return  "https://www.bing.com" + bing.getImages().get(0).getUrl();
    }

    @Override
    public void getRandomImage(HttpServletResponse response, String i18nKey) {
        BingJsonI18nEnum i18nEnum = EnumUtils.getEnum(BingJsonI18nEnum.class, i18nKey);
        List<BingWallpaperPO> list = repository.findByI18nKey(i18nEnum.getKey());
        if (list.isEmpty()) {
            throw new RandomImgException("暂无数据，请稍后再试", 50001);
        }
        int randomInt = RandomUtil.randomInt(0, list.size());
        BingWallpaperPO bingWallpaperPO = list.get(randomInt);
        log.info("请求数据：{}", JSON.toJSONString(bingWallpaperPO));
        streamImage(response, bingWallpaperPO.getUrl());
    }

    private void streamImage(HttpServletResponse response, String imageUrl) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(imageUrl).openConnection();
            int status = conn.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK && !StringUtils.equals(imageUrl, getTodayWallpaperUrl())) {
                log.error("对应链接可能已失效，默认展示今日壁纸，请求失败 url：{}", imageUrl);
                getTodayWallpaper(response);
            } else if (status != HttpURLConnection.HTTP_OK && StringUtils.equals(imageUrl, getTodayWallpaperUrl())) {
                throw new RandomImgException("请求失败，对应链接可能已失效，请刷新重试！", 50002);
            }
        } catch (IOException e) {
            throw new RandomImgException(e);
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
            // 使用日志打印图片大小
            if (contentLength > 0) {
                double sizeMB = contentLength / 1024.0 / 1024.0;
                log.info("图片 header 大小: {} MB", String.format("%.2f", sizeMB));
            }
            double streamSizeMB = totalBytes / 1024.0 / 1024.0;
            log.info("图片 stream 大小: {} MB", String.format("%.2f", streamSizeMB));
        } catch (ClientAbortException e) {
            log.warn("传输失败: {}, 失败原因: {}", imageUrl, e.getMessage());
            throw new RandomImgException("客户端在传输过程中断开连接，图片未传输完毕: " + e.getMessage() + "已传输大小: " + String.format("%.2f", totalBytes / 1024.0 / 1024.0) + "MB", 50003);
        } catch (IOException e) {
            throw new RandomImgException(e);
        } finally {
            conn.disconnect();
        }
    }
}
