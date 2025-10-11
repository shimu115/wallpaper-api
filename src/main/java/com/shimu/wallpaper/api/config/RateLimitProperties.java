package com.shimu.wallpaper.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 限流配置 TODO 待完善
 */
@Data
@Component
@ConfigurationProperties(prefix = "rate-limit")
public class RateLimitProperties {

    private boolean enabled = true;

    private List<Rule> rules;

    @Data
    public static class Rule {
        private String path;   // 匹配路径
        private double qps;    // 每秒请求数
        private Boolean enabled;
    }
}
