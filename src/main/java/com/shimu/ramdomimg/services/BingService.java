package com.shimu.ramdomimg.services;

import javax.servlet.http.HttpServletResponse;

public interface BingService {


    void getTodayWallpaper(HttpServletResponse response);

    void getRandomImage(HttpServletResponse response, String i18nKey);
}
