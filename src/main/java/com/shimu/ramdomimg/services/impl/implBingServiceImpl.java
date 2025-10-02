package com.shimu.ramdomimg.services.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shimu.ramdomimg.enums.ApiContains;
import com.shimu.ramdomimg.enums.BingJsonI18nEnum;
import com.shimu.ramdomimg.exception.RandomImgException;
import com.shimu.ramdomimg.model.response.BingResponse;
import com.shimu.ramdomimg.model.response.GitHubJsonResponse;
import com.shimu.ramdomimg.model.response.GitHubJsonResult;
import com.shimu.ramdomimg.services.BingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
public class implBingServiceImpl implements BingService {
    @Override
    public void getTodayWallpaper(HttpServletResponse response) {
        // 1. 请求 Bing 官方 JSON 接口
        String bingApi = ApiContains.BING_PHOTO_API;
        RestTemplate restTemplate = new RestTemplate();
        BingResponse bing = restTemplate.getForObject(bingApi, BingResponse.class);

        // 2. 拼接完整图片地址
        assert bing != null;
        String imageUrl = "https://www.bing.com" + bing.getImages().get(0).getUrl();
        streamImage(response, imageUrl);
    }

    @Override
    public void getRandomImage(HttpServletResponse response, String i18nKey) {
        String resp = HttpUtil.get(getBingRandomApi(i18nKey));
        GitHubJsonResult<List<GitHubJsonResponse>> gitHubJsonResult = JSONObject.parseObject(resp, new TypeReference<GitHubJsonResult<List<GitHubJsonResponse>>>(){});

        if (gitHubJsonResult == null) {
            throw new RandomImgException("获取数据失败，请稍后尝试~~~");
        }
        if (gitHubJsonResult.getCode() != 200) {
            throw new RandomImgException("请求异常，请检查 api 请求是否正确...");
        }
        List<GitHubJsonResponse> data = gitHubJsonResult.getData();
        int size = data.size();
        int randomInt = RandomUtil.randomInt(0, size - 1);
        GitHubJsonResponse gitHubJsonResponse = data.get(randomInt);
        log.info("请求数据：{}", JSON.toJSONString(gitHubJsonResponse));
        streamImage(response, gitHubJsonResponse.getUrl());
    }

    private String getBingRandomApi (String i18nKey) {
        BingJsonI18nEnum enumResult = EnumUtils.getEnum(BingJsonI18nEnum.class, i18nKey);
        if (enumResult == null) {
            throw new RandomImgException("无效参数");
        }
        return StringUtils.replace(ApiContains.BING_GITHUB_JSON, "{i18n_key}", enumResult.getKey());
    }

    private void streamImage(HttpServletResponse response, String imageUrl) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(imageUrl).openConnection();
            int status = conn.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
                return;
            }
        } catch (IOException e) {
            throw new RandomImgException(e);
        }

        String contentType = conn.getContentType();
        int contentLength = conn.getContentLength();

        if (contentType != null) response.setContentType(contentType);
        if (contentLength > 0) response.setContentLength(contentLength);

        long totalBytes = 0; // 累加字节数
        try (InputStream in = conn.getInputStream();
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                totalBytes += len;
            }
            out.flush();
            // 使用日志打印图片大小
            if (contentLength > 0) {
                double sizeMB = contentLength / 1024.0 / 1024.0;
                log.info("图片 header 大小: {} MB", String.format("%.2f", sizeMB));
            }
            double streamSizeMB = totalBytes / 1024.0 / 1024.0;
            log.info("图片 stream 大小: {} MB", String.format("%.2f", streamSizeMB));
        } catch (ClientAbortException e) {
            log.warn("客户端在传输过程中断开连接，图片未传输完毕: {}, 已传输大小: {} MB", e.getMessage(), totalBytes / 1024.0 / 1024.0);
        } catch (IOException e) {
            throw new RandomImgException(e);
        } finally {
            conn.disconnect();
        }
    }
}
