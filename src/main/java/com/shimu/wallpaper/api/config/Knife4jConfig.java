package com.shimu.wallpaper.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI globalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wallpaper API 文档")
                        .description("提供壁纸相关接口")
                        .version(VersionProperties.getVersion()) // 在这里设置版本
                        .contact(new Contact()
                                .name("shimu")
                                .email("shimu@shimupersonal.top")
                                .url("https://github.com/shimu115/wallpaper-api"))
                        .license(license())
                );
    }

    @Bean
    public GroupedOpenApi wallpaperApi() {
        log.info("wallpaper api version is {}", VersionProperties.getVersion());
        return GroupedOpenApi.builder()
                .group("Wallpaper API")
                .pathsToMatch("/api/**")
                .packagesToScan("com.shimu.wallpaper.api.controller")
                .build();
    }

    private License license() {
        return new License()
                .name("Apache License 2.0")
                .url("https://github.com/shimu115/wallpaper-api/blob/main/LICENSE");
    }
}
