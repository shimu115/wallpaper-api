package com.shimu.wallpaper.api.model;

import lombok.Data;
import java.util.List;
import java.util.LinkedHashMap;

/**
 * mirror 缓存，对应 data/mirror.json
 */
@Data
public class MirrorCache {

    /** 缓存时的源 mirror 列表，用于检测配置变更 */
    private List<String> sourceMirrors;

    /** 解析后的 mirror 映射，key 如 github_mirror_0 */
    private LinkedHashMap<String, MirrorCacheItem> mirror;
}