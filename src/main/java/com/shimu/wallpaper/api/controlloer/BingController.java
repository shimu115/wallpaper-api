package com.shimu.wallpaper.api.controlloer;

import com.shimu.wallpaper.api.exception.WallpaperApiException;
import com.shimu.wallpaper.api.services.BingService;
import com.shimu.wallpaper.api.services.server.BingScheduledService;
import com.shimu.wallpaper.api.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("bing/wallpaper")
@Slf4j
public class BingController {

    @Autowired
    private BingService bingService;

    @Autowired
    private BingScheduledService bingScheduledService;

    /**
     * bing 每日壁纸接口
     *
     * @param response (无需传参)
     * @throws IOException
     */
    @GetMapping("/today")
    public void getTodayWallpaper(HttpServletResponse response) {
        bingService.getTodayWallpaper(response);
    }

    /**
     * bing 随机图片
     *
     * @param response
     * @param i18nKey
     */
    @GetMapping("/random")
    public void getRandomImage(HttpServletResponse response,
                               @RequestParam(required = false) String i18nKey) {
        if (!bingScheduledService.isInitialized()) {
            throw new WallpaperApiException("未完成初始化，请稍后~~~", 10000);
        }
        bingService.getRandomImage(response, i18nKey);
    }

    @GetMapping("fresh_data")
    public ResultUtils<Void> freshData() {
        bingScheduledService.refreshAllLanguages();
        return ResultUtils.success();
    }
}
