package com.shimu.wallpaper.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Slf4j
public class WallpaperApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WallpaperApiApplication.class, args);
        log.info("************* 启动成功 *************");
    }

}
