package com.shimu.wallpaper.api.services.impl;

import cn.hutool.core.util.ObjectUtil;
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
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * bing 壁纸业务代码
 */
@Service
@Slf4j
public class BingServiceImpl implements BingService {

    @Autowired
    private BingScheduledService bingScheduledService;

    @Autowired
    private BingWallpaperRepository repository;

    /**
     * 获取今日壁纸
     * @param response
     */
    @Override
    public void getTodayWallpaper(HttpServletResponse response) {
        StreamResponseUtils.streamImage(response, getTodayWallpaperUrl());
    }

    private String getTodayWallpaperUrl() {
        // 1. 请求 Bing 官方 JSON 接口
        String bingApi = ApiContains.BING_PHOTO_API;
        RestTemplate restTemplate = new RestTemplate();
        BingResponse bing = restTemplate.getForObject(bingApi, BingResponse.class);

        if (ObjectUtils.isEmpty(bing)) {
            throw new WallpaperApiException("请求失败，请稍后再试", 50001);
        }
        // 2. 拼接完整图片地址
        return  "https://www.bing.com" + bing.getImages().get(0).getUrl();
    }

    /**
     * 获取随机壁纸
     * @param response
     * @param i18nKey
     */
    @Override
    public void getRandomImage(HttpServletResponse response, String i18nKey) {
        List<BingWallpaperPO> list = null;
        BingJsonI18nEnum i18nEnum = null;
        if (StringUtils.isNotEmpty(i18nKey)) {
            i18nEnum = EnumUtils.getEnum(BingJsonI18nEnum.class, i18nKey);
            list = repository.findByI18nKey(i18nEnum.getKey());
        } else {
            list = repository.findAll();
        }
        log.info("i18nEnum:{}", i18nEnum == null ? null : i18nEnum.toString());
        log.info("查出结果 {} 条", list.size());
        if (list.isEmpty()) {
            throw new WallpaperApiException("暂无数据，请稍后再试", 50001);
        }
        int randomInt = RandomUtil.randomInt(0, list.size());
        BingWallpaperPO bingWallpaperPO = list.get(randomInt);
        log.info("请求数据：{}", JSON.toJSONString(bingWallpaperPO));
        StreamResponseUtils.streamImage(response, bingWallpaperPO.getUrl());
    }
}
