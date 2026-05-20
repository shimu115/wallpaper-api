package com.shimu.wallpaper.api.component;

import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.utils.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * github 镜像地址
 */
@Component
@ConfigurationProperties(prefix = "mirror")
@Data
@Slf4j
public class MirrorComponent {

    private List<String> github;
    private Integer retryCount = 3;

    // 用 static 保存单例引用
    private static MirrorComponent instance;

    @PostConstruct
    public void init() {
        instance = this;
        log.info("MirrorComponent 初始化成功: {}", JSON.toJSONString(github));
    }

    public static String getBingGithubJson() {
        String string = "https://raw.githubusercontent.com";
        if (instance == null) {
            log.warn("MirrorComponent 未初始化，使用默认地址");
            return string;
        }

        List<String> githubMirror = instance.getGithub();
        if (githubMirror == null || githubMirror.isEmpty()) {
            log.warn("mirror.github 配置为空，使用默认地址");
            return string;
        }

        for (String mirror : githubMirror) {
            boolean reachable = HttpUtils.isReachable(mirror);
            if (reachable) {
                return StringUtils.endsWith(mirror, "/") ? StringUtils.removeEnd(mirror, "/") : mirror;
            }
        }
        log.warn("mirror.github 设置的镜像全部不可用，使用默认地址，请检查配置是否有误");
        return string;
    }
}

