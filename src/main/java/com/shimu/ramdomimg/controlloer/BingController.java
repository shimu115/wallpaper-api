package com.shimu.ramdomimg.controlloer;

import com.shimu.ramdomimg.enums.ApiContains;
import com.shimu.ramdomimg.exception.RandomImgException;
import com.shimu.ramdomimg.model.response.BingResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("bing")
public class BingController {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * bing 每日壁纸接口
     * @param response (无需传参)
     * @throws IOException
     */
    @GetMapping("/getTodayWallpaper")
    public void getTodayWallpaper(HttpServletResponse response) {
        // 1. 请求 Bing 官方 JSON 接口
        String bingApi = ApiContains.BING_PHOTO_API;
        RestTemplate restTemplate = new RestTemplate();
        BingResponse bing = restTemplate.getForObject(bingApi, BingResponse.class);

        // 2. 拼接完整图片地址
        assert bing != null;
        String imageUrl = "https://www.bing.com" + bing.getImages().get(0).getUrl();
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

        try (InputStream in = conn.getInputStream();
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            throw new RandomImgException(e);
        } finally {
            conn.disconnect();
        }
    }

    // TODO bing 随机壁纸接口
    @GetMapping("getRandomImage")
    public void getRandomImage(HttpServletResponse response) throws IOException {

    }
}
