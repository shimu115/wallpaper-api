package com.shimu.ramdomimg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RandomImgApplication {

    public static void main(String[] args) {
        SpringApplication.run(RandomImgApplication.class, args);
        log.info("************* 启动成功 *************");
    }

}
