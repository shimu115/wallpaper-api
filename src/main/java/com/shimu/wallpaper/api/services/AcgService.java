package com.shimu.wallpaper.api.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AcgService {
    void random(HttpServletResponse response, HttpServletRequest request);
}
