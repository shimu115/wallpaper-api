package com.shimu.wallpaper.api.model;

import lombok.Data;

/**
 * 单个 mirror 缓存项
 */
@Data
public class MirrorCacheItem {

    /** 拼接后的完整 URL（含 {i18n_key} 占位符） */
    private String url;

    /** 使用的方式: replace / prefix_http / prefix */
    private String method;
}