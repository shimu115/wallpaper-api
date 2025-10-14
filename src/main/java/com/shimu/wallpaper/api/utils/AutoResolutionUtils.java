package com.shimu.wallpaper.api.utils;

import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.model.Resolution;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AutoResolutionUtils {

    private String userAgent;
    private String i18nKey;
    private String url;
    private Resolution resolution;
    private Resolution customizeResolution;

    public static void main(String[] args) {
        AutoResolutionUtils autoResolutionUtils = AutoResolutionUtils.builder()
                .i18nKey("zh_CN")
                .userAgent("windows10")
                .url("https://www.bing.com/th?id=OHR.HinterseeWaterfall_ROW0045640204_{res}")
                .build();
        String wallpaperUrl = autoResolutionUtils.autoResolutionWallpaperUrl(new Resolution());
        System.out.println(wallpaperUrl);
    }

    public void autoResolution() {
        this.resolution = Resolution.builder().height(ResolutionEnum.DESKTOP.getHeight()).width(ResolutionEnum.DESKTOP.width).build();
        int width = (this.customizeResolution.getWidth() == null || this.customizeResolution.getWidth() == 0) ? 1920 : this.customizeResolution.getWidth();
        int height = (this.customizeResolution.getHeight() == null || this.customizeResolution.getHeight() == 0) ? 1080 : this.customizeResolution.getHeight();
        if (StringUtils.containsIgnoreCase(userAgent, "mobile") ||
                StringUtils.containsIgnoreCase(userAgent, "android") ||
                StringUtils.containsIgnoreCase(userAgent, "iphone")) {
            width = width == 1920 ? 1080 : height;
            height = height == 1080 ? 1920 : width;
            this.resolution = Resolution.builder().height(ResolutionEnum.MOBILE.getHeight()).width(ResolutionEnum.MOBILE.width).build();
        }
        if (StringUtils.containsIgnoreCase(userAgent, "ipad")) {
            height = height == 1080 ? 1200 : height;
            this.resolution = Resolution.builder().height(ResolutionEnum.IPAD.getHeight()).width(ResolutionEnum.IPAD.width).build();
        }
        this.customizeResolution = Resolution.builder().height(height).width(width).build();
        log.info("userAgent: {}", userAgent);
    }

    public String autoResolutionWallpaperUrl(Resolution customizeResolution) {
        return AutoResolutionUtils.autoResolutionWallpaperUrl(userAgent, i18nKey, url, customizeResolution);
    }

    public static String autoResolutionWallpaperUrl(String userAgent, String i18nKey, String url, Resolution customizeResolution) {
        AutoResolutionUtils autoResolutionUtils = AutoResolutionUtils.builder()
                .userAgent(userAgent)
                .i18nKey(i18nKey)
                .url(url)
                .customizeResolution(customizeResolution)
                .build();
        autoResolutionUtils.autoResolution();
        if (StringUtils.contains(url, "{res}")) {
            url = StringUtils.replace(url, "{res}", autoResolutionUtils.resolution.toString() + ".jpg");
        }
        if (StringUtils.contains(url, "1920x1080")) {
            url = StringUtils.replace(url, "1920x1080", autoResolutionUtils.resolution.toString());
        }
        return url + String.format("&w=%d&h=%d", autoResolutionUtils.customizeResolution.getWidth(), autoResolutionUtils.customizeResolution.getHeight());
    }

    @AllArgsConstructor
    @Getter
    private enum ResolutionEnum {
        MOBILE(1080, 1920),
        DESKTOP(1920, 1080),
        IPAD(1920, 1200),
        ;

        private final Integer width;
        private final Integer height;
    }
}
