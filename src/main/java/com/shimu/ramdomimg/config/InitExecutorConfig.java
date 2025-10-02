package com.shimu.ramdomimg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
