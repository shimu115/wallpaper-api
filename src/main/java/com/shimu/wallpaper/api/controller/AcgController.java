package com.shimu.wallpaper.api.controller;

import com.shimu.wallpaper.api.enums.AskMethod;
import com.shimu.wallpaper.api.services.AcgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping({"/acg/wallpaper", "/acg"})
@Tag(name = "acg 图片", description = "随机 acg 图片")
public class AcgController {

    @Autowired
    private AcgService acgService;

    @GetMapping("/random")
    @Operation(summary = "随机图片")
    public void random(
            @RequestParam(required = false) AskMethod askMethod,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        if (ObjectUtils.isEmpty(askMethod)) {
            askMethod = AskMethod.STREAM;
        }
        acgService.random(askMethod, response, request);
    }
}
