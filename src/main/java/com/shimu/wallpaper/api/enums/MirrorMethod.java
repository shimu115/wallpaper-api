package com.shimu.wallpaper.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 镜像地址的替换/拼接方式
 */
@Getter
@AllArgsConstructor
public enum MirrorMethod {

    /** 替换域名 */
    REPLACE("replace"),
    /** 带协议拼接（原 URL 保留协议拼接到 mirror 后面） */
    PREFIX_HTTP("prefix_http"),
    /** 不带协议拼接（原 URL 去掉协议后拼接到 mirror 后面） */
    PREFIX("prefix");

    private final String value;

    public static MirrorMethod fromValue(String value) {
        for (MirrorMethod m : values()) {
            if (m.value.equals(value)) {
                return m;
            }
        }
        return null;
    }
}