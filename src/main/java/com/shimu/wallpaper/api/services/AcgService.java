package com.shimu.wallpaper.api.services;

import com.shimu.wallpaper.api.enums.AskMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AcgService {
    void random(AskMethod askMethod, HttpServletResponse response, HttpServletRequest request);
}
