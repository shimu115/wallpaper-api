package com.shimu.wallpaper.api.services;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface BingService {


    void getTodayWallpaper(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height);

    void getRandomImage(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height);

    Map<String, Object> getI18n();
}
