package com.shimu.wallpaper.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class Knife4jConfig {


    @Bean
    public GroupedOpenApi wallpaperApi() {
        return GroupedOpenApi.builder()
                .group("Wallpaper API")
                .pathsToMatch("/api/**")   // 重点！！！
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定扫描 controller 包路径
                .apis(RequestHandlerSelectors.basePackage("com.shimu.wallpaper.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        log.info("wallpaper api version is {}", VersionProperties.getVersion());
        return new ApiInfoBuilder()
                .title("Wallpaper API 文档")
                .description("Bing 壁纸接口服务文档，支持多语言与 SQLite 持久化存储")
                .version(VersionProperties.getVersion())
                .build();
    }
}
