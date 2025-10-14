package com.shimu.wallpaper.api.services;

import javax.servlet.http.HttpServletResponse;

public interface BingService {


    void getTodayWallpaper(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height);

    void getRandomImage(HttpServletResponse response, String userAgent, String i18nKey, Integer width, Integer height);
}
