package com.shimu.ramdomimg.controlloer;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shimu.ramdomimg.services.BingService;
import com.shimu.ramdomimg.enums.ApiContains;
import com.shimu.ramdomimg.enums.BingJsonI18nEnum;
import com.shimu.ramdomimg.exception.RandomImgException;
import com.shimu.ramdomimg.model.response.BingResponse;
import com.shimu.ramdomimg.model.response.GitHubJsonResponse;
import com.shimu.ramdomimg.model.response.GitHubJsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("bing")
@Slf4j
public class BingController {

    @Autowired
    private BingService bingService;

    /**
     * bing 每日壁纸接口
     * @param response (无需传参)
     * @throws IOException
     */
    @GetMapping("/todayWallpaper")
    public void getTodayWallpaper(HttpServletResponse response) {
        bingService.getTodayWallpaper(response);
    }

    @GetMapping("randomImage")
    public void getRandomImage(HttpServletResponse response,
                               @RequestParam String i18nKey) {
        bingService.getRandomImage(response, i18nKey);

    }
}
