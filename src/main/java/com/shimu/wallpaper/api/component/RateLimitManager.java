//package com.shimu.ramdomimg.component;
//
//import com.google.common.util.concurrent.RateLimiter;
//import com.shimu.ramdomimg.config.RateLimitProperties;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class RateLimitManager {
//
//    private final RateLimitProperties properties;
//    private final Map<String, RateLimiter> limiterMap = new ConcurrentHashMap<>();
//
//    public RateLimitManager(RateLimitProperties properties) {
//        this.properties = properties;
//    }
//
//    @PostConstruct
//    public void init() {
//        if (!properties.isEnabled()) return;
//
//        for (RateLimitProperties.Rule rule : properties.getRules()) {
//            if (!rule.getEnabled()) continue;
//            limiterMap.put(rule.getPath(), RateLimiter.create(rule.getQps()));
//        }
//    }
//
//    public boolean tryAcquire(String path) {
//        if (!properties.isEnabled()) return true;
//
//        for (Map.Entry<String, RateLimiter> entry : limiterMap.entrySet()) {
//            String pattern = entry.getKey();
//            if (path.matches(pattern.replace("**", ".*"))) {
//                return entry.getValue().tryAcquire();
//            }
//        }
//        return true; // 未配置限流则默认放行
//    }
//}
