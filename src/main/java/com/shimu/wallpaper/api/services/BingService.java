package com.shimu.wallpaper.api.services;

import com.shimu.wallpaper.api.enums.AskMethod;
import com.shimu.wallpaper.api.enums.Sort;
import com.shimu.wallpaper.api.model.Page;
import com.shimu.wallpaper.api.model.vo.BingWallpaperVO;
import com.shimu.wallpaper.api.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface BingService {


    void getTodayWallpaper(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height, AskMethod askMethod);

    void getRandomImage(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height, AskMethod askMethod);

    Map<String, Object> getI18n();

    Page<BingWallpaperVO> findPage(String i18nKey, Sort sort, Integer page, Integer pageSize);

    List<BingWallpaperVO> find(String i18nKey, Integer dataId, String startTime, String endTime, Sort sort);
}
