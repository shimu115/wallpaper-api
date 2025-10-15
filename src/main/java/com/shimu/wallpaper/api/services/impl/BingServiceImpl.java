package com.shimu.wallpaper.api.services.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.enums.BingJsonI18nEnum;
import com.shimu.wallpaper.api.exception.WallpaperApiException;
import com.shimu.wallpaper.api.model.Resolution;
import com.shimu.wallpaper.api.model.po.BingWallpaperPO;
import com.shimu.wallpaper.api.model.vo.BingWallpaperVO;
import com.shimu.wallpaper.api.repository.BingWallpaperRepository;
import com.shimu.wallpaper.api.services.BingService;
import com.shimu.wallpaper.api.services.server.BingScheduledService;
import com.shimu.wallpaper.api.utils.AutoResolutionUtils;
import com.shimu.wallpaper.api.utils.StreamResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
    public void getTodayWallpaper(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height) {
        String todayWallpaperUrl = StreamResponseUtils.getTodayWallpaperUrl(i18nKey);
        Resolution customizeResolution = Resolution.builder().width(width).height(height).build();
        String wallpaperUrl = AutoResolutionUtils.autoResolutionWallpaperUrl(userAgent, i18nKey, todayWallpaperUrl, customizeResolution);
        StreamResponseUtils.streamImage(response, wallpaperUrl, userAgent, i18nKey, width, height);
    }

    /**
     * 获取随机壁纸
     * @param response
     * @param i18nKey
     */
    @Override
    public void getRandomImage(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height) {
        List<BingWallpaperPO> list = null;
        BingJsonI18nEnum i18nEnum = null;
        if (StringUtils.isNotEmpty(i18nKey)) {
            i18nEnum = EnumUtils.getEnum(BingJsonI18nEnum.class, i18nKey);
            list = repository.findByI18nKey(i18nEnum.getKey());
        } else {
            BingJsonI18nEnum[] values = BingJsonI18nEnum.values();
            i18nEnum = values[RandomUtil.randomInt(0, values.length)];
            list = repository.findByI18nKey(i18nEnum.getKey());
        }
        log.info("i18nEnum:{}", i18nEnum);
        log.info("查出结果 {} 条", list.size());
        if (list.isEmpty()) {
            throw new WallpaperApiException("暂无数据，请稍后再试", 50001);
        }
        int randomInt = RandomUtil.randomInt(0, list.size());
        BingWallpaperPO bingWallpaperPO = list.get(randomInt);
        BingWallpaperVO bingWallpaperVO = BeanUtil.copyProperties(bingWallpaperPO, BingWallpaperVO.class, "url");
        Resolution customizeResolution = Resolution.builder().width(width).height(height).build();
        String appendUrl = AutoResolutionUtils.autoResolutionWallpaperUrl(userAgent, i18nEnum.name(), bingWallpaperPO.getUrl(), customizeResolution);
        bingWallpaperVO.setUrlList(Collections.singletonList(appendUrl));
        log.info("查询结果：{}", JSON.toJSONString(bingWallpaperVO));
        StreamResponseUtils.streamImage(response, appendUrl, userAgent, i18nEnum.name(), width, height);
    }

    @Override
    public Map<String, Object> getI18n() {
        return com.shimu.wallpaper.api.utils.EnumUtils.enumToMap(BingJsonI18nEnum.class);
    }
}
