package com.shimu.wallpaper.api.component;

import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.utils.HttpUtils;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "mirror")
@Data
@Slf4j
public class MirrorComponent {

    private List<String> github;

    // 用 static 保存单例引用
    private static MirrorComponent instance;

    @PostConstruct
    public void init() {
        instance = this;
        log.info("MirrorComponent 初始化成功: {}", JSON.toJSONString(github));
    }

    public static String getBingGithubJson() {
        if (instance == null) {
            log.warn("MirrorComponent 未初始化，使用默认地址");
            return "https://raw.githubusercontent.com";
        }

        List<String> githubMirror = instance.getGithub();
        log.info("githubMirror:{}", JSON.toJSONString(githubMirror));
        for (String mirror : githubMirror) {
            boolean reachable = HttpUtils.isReachable(mirror);
            if (reachable) {
                String url = StringUtils.endsWith(mirror, "/") ? StringUtils.removeEnd(mirror, "/") : mirror;
                log.info("[MirrorComponent] {} is reachable", url);
                return url;
            }
        }
        log.info("[MirrorComponent] https://raw.githubusercontent.com is reachable");
        return "https://raw.githubusercontent.com";
    }
}

