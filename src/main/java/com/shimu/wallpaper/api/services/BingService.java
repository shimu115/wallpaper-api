package com.shimu.wallpaper.api.services;

import com.shimu.wallpaper.api.model.vo.BingWallpaperVO;
import com.shimu.wallpaper.api.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface BingService {


    void getTodayWallpaper(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height);

    void getRandomImage(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height);

    Map<String, Object> getI18n();

    PageUtils<BingWallpaperVO> find(String i18nKey, Integer page, Integer pageSize);
}
