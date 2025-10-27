package com.shimu.wallpaper.api.controller;

import com.shimu.wallpaper.api.services.AcgService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/acg/wallpaper")
@Tag(name = "acg 图片", description = "随机 acg 图片")
public class AcgController {

    @Autowired
    private AcgService acgService;

    @GetMapping("/random")
    public void random(HttpServletResponse response, HttpServletRequest request) {
        acgService.random(response, request);
    }
}
