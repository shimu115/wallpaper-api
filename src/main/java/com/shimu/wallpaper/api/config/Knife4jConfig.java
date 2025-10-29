package com.shimu.wallpaper.api.config;

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
    public GroupedOpenApi wallpaperApi() {
        log.info("wallpaper api version is {}", VersionProperties.getVersion());
        return GroupedOpenApi.builder()
                .group("Wallpaper API")
                .pathsToMatch("/api/**")
                .packagesToScan("com.shimu.wallpaper.api.controller")
                .addOpenApiCustomiser(openApi -> openApi.info(new Info()
                        .license(license())
                        .title("Wallpaper API 文档")
                        .description("提供壁纸相关接口")
                        .version(VersionProperties.getVersion())
                        .contact(new Contact()
                                .name("shimu")
                                .email("shimuspace@email.com")
                                .url("https://github.com/shimu115/wallpaper-api"))))
                .build();
    }

    private License license() {
        return new License()
                .name("Apache License 2.0")
                .url("https://github.com/shimu115/wallpaper-api/blob/main/LICENSE");
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                // 指定扫描 controller 包路径
//                .apis(RequestHandlerSelectors.basePackage("com.shimu.wallpaper.api"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        log.info("wallpaper api version is {}", VersionProperties.getVersion());
//        return new ApiInfoBuilder()
//                .title("Wallpaper API 文档")
//                .description("Bing 壁纸接口服务文档，支持多语言与 SQLite 持久化存储")
//                .version(VersionProperties.getVersion())
//                .build();
//    }
}
