package com.shimu.wallpaper.api.config;

import cn.hutool.core.io.IoUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class VersionProperties {

    public static String getVersion() {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("build.version");
            return Optional.ofNullable(properties.getProperty("version")).orElse("unknown");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load build.version");
        }
    }
}
