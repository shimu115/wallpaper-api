package com.shimu.wallpaper.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 初始化连接池，用于初始化数据
 */
@Configuration
public class InitExecutorConfig {

    @Bean(name = "initExecutor")
    public Executor initExecutor() {
        return Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("bing-init-thread");
            t.setDaemon(true);
            return t;
        });
    }
}
