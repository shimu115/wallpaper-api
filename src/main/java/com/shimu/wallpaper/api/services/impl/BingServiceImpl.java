package com.shimu.wallpaper.api.services.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.enums.ApiContains;
import com.shimu.wallpaper.api.enums.BingJsonI18nEnum;
import com.shimu.wallpaper.api.exception.WallpaperApiException;
import com.shimu.wallpaper.api.model.po.BingWallpaperPO;
import com.shimu.wallpaper.api.model.response.BingResponse;
import com.shimu.wallpaper.api.repository.BingWallpaperRepository;
import com.shimu.wallpaper.api.services.BingService;
import com.shimu.wallpaper.api.services.server.BingScheduledService;
import com.shimu.wallpaper.api.utils.StreamResponseUtils;
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
public class BingServiceImpl implements BingService {

    @Autowired
    private BingScheduledService bingScheduledService;

    @Autowired
    private BingWallpaperRepository repository;

    @Override
    public void getTodayWallpaper(HttpServletResponse response) {
        StreamResponseUtils.streamImage(response, getTodayWallpaperUrl());
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
            throw new WallpaperApiException("暂无数据，请稍后再试", 50001);
        }
        int randomInt = RandomUtil.randomInt(0, list.size());
        BingWallpaperPO bingWallpaperPO = list.get(randomInt);
        log.info("请求数据：{}", JSON.toJSONString(bingWallpaperPO));
        StreamResponseUtils.streamImage(response, bingWallpaperPO.getUrl());
    }
}
