package com.shimu.wallpaper.api.controlloer;

import cn.hutool.core.date.DateUtil;
import com.shimu.wallpaper.api.enums.SortEnum;
import com.shimu.wallpaper.api.exception.WallpaperApiException;
import com.shimu.wallpaper.api.model.vo.BingWallpaperVO;
import com.shimu.wallpaper.api.services.BingService;
import com.shimu.wallpaper.api.services.server.BingScheduledService;
import com.shimu.wallpaper.api.utils.PageUtils;
import com.shimu.wallpaper.api.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    public void getTodayWallpaper(HttpServletResponse response,
                                  @RequestHeader(value = "User-Agent") String userAgent,
                                  @RequestParam(required = false, defaultValue = "zh_CN") String i18nKey,
                                  @RequestParam(required = false, defaultValue = "1920") Integer width,
                                  @RequestParam(required = false, defaultValue = "1080") Integer height) {
        if (StringUtils.isEmpty(userAgent)) {
            throw new WallpaperApiException("请求头缺少 User-Agent 参数", 10001);
        }
        bingService.getTodayWallpaper(response, userAgent, i18nKey, width, height);
    }

    /**
     * bing 随机图片
     *
     * @param response
     * @param i18nKey
     */
    @GetMapping("/random")
    public void getRandomImage(HttpServletResponse response,
                               @RequestHeader(value = "User-Agent") String userAgent,
                               @RequestParam(required = false) String i18nKey,
                               @RequestParam(required = false, defaultValue = "1920") Integer width,
                               @RequestParam(required = false, defaultValue = "1080") Integer height) {
        if (StringUtils.isEmpty(userAgent)) {
            throw new WallpaperApiException("请求头缺少 User-Agent 参数", 10001);
        }
        if (!bingScheduledService.isInitialized()) {
            throw new WallpaperApiException("未完成初始化，请稍后~~~", 10000);
        }
        bingService.getRandomImage(response, userAgent, i18nKey, width, height);
    }

    /**
     * 手动刷新数据
     * @return
     */
    @GetMapping("fresh_data")
    public ResultUtils<Void> freshData() {
        bingScheduledService.refreshAllLanguages();
        return ResultUtils.success();
    }

    /**
     * 获取可使用的语言数据
     * @return
     */
    @GetMapping("getI18n")
    public ResultUtils<Map<String, Object>> getI18n() {
        Map<String, Object> result = bingService.getI18n();
        return ResultUtils.success(result);
    }

    /**
     * 分页查询数据
     * @param i18nKey
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("findPage")
    public ResultUtils<PageUtils<BingWallpaperVO>> findPage(@RequestParam(required = false) String i18nKey,
                                                            @RequestParam(required = false, defaultValue = "desc") String order,
                                                            @RequestParam(required = false, defaultValue = "1") Integer page,
                                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Integer sort = EnumUtils.getEnum(SortEnum.class, order.toUpperCase(Locale.ROOT)) == null ?
                null : EnumUtils.getEnum(SortEnum.class, order.toUpperCase(Locale.ROOT)).getValue();
        if (sort == null) {
            throw new WallpaperApiException("排序参数错误", 10002);
        }
        PageUtils<BingWallpaperVO> result = bingService.findPage(i18nKey, sort, page, pageSize);
        return ResultUtils.success(result);
    }

    @GetMapping("find")
    public ResultUtils<List<BingWallpaperVO>> find(@RequestParam(required = false) String i18nKey,
                                                   @RequestParam(required = false) Integer dataId,
                                                   @RequestParam(required = false)
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") String startTime,
                                                   @RequestParam(required = false)
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") String endTime,
                                                   @RequestParam(required = false, defaultValue = "desc") String order) {
        Integer sort = EnumUtils.getEnum(SortEnum.class, order.toUpperCase(Locale.ROOT)) == null ?
                null : EnumUtils.getEnum(SortEnum.class, order.toUpperCase(Locale.ROOT)).getValue();
        if (sort == null) {
            throw new WallpaperApiException("排序参数错误", 10002);
        }
        List<BingWallpaperVO> result = bingService.find(i18nKey, dataId, startTime, endTime, sort);
        return ResultUtils.success(result);
    }

}
