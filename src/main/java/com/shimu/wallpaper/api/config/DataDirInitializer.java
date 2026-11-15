package com.shimu.wallpaper.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 启动阶段提前创建数据目录。
 * <p>
 * SQLite 只会创建数据库文件，不会自动创建数据库文件所在的父目录，
 * 因此首次启动（data 目录不存在）时会因找不到路径导致数据源初始化失败。
 * 该初始化器在 Spring 容器刷新（数据源初始化）之前执行，确保目录已存在。
 */
@Slf4j
public class DataDirInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String SQLITE_URL_PREFIX = "jdbc:sqlite:";
    private static final String DEFAULT_DATA_DIR = "data";

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        Set<Path> dirs = new LinkedHashSet<>();

        // SQLite 数据库文件所在目录
        Path sqliteDir = resolveSqliteDir(context.getEnvironment().getProperty("spring.datasource.url"));
        if (sqliteDir != null) {
            dirs.add(sqliteDir);
        }
        // mirror 缓存固定写入 data 目录（见 MirrorResolver）
        dirs.add(Paths.get(DEFAULT_DATA_DIR).toAbsolutePath());

        for (Path dir : dirs) {
            try {
                boolean exists = Files.exists(dir);
                Files.createDirectories(dir);
                if (!exists) {
                    log.info("已创建数据目录: {}", dir);
                }
            } catch (IOException e) {
                throw new IllegalStateException("创建数据目录失败: " + dir, e);
            }
        }
    }

    /**
     * 从 jdbc:sqlite: 连接串中解析出数据库文件所在目录，
     * 非 SQLite 或内存数据库返回 null。
     */
    private Path resolveSqliteDir(String url) {
        if (url == null || !url.regionMatches(true, 0, SQLITE_URL_PREFIX, 0, SQLITE_URL_PREFIX.length())) {
            return null;
        }
        String path = url.substring(SQLITE_URL_PREFIX.length());
        int paramIndex = path.indexOf('?');
        if (paramIndex >= 0) {
            path = path.substring(0, paramIndex);
        }
        if (path.trim().isEmpty() || ":memory:".equals(path) || path.startsWith("file:")) {
            return null;
        }
        return Paths.get(path).toAbsolutePath().normalize().getParent();
    }
}
