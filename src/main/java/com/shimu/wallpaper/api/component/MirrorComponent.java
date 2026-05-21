package com.shimu.wallpaper.api.component;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * mirror 配置属性绑定
 */
@Component
@ConfigurationProperties(prefix = "mirror")
@Data
@Slf4j
public class MirrorComponent {

    private List<String> github;
    private Integer retryCount = 3;

    @PostConstruct
    public void init() {
        log.info("MirrorComponent 初始化: github={}, retryCount={}", JSON.toJSONString(github), retryCount);
    }
}