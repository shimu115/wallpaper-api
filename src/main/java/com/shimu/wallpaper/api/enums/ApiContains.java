package com.shimu.wallpaper.api.enums;

/**
 * 存放第三方 api 地址
 */
public class ApiContains {

    // https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN
    public static String BING_PHOTO_API = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt={i18nKey}";
    // 文件位置：https://github.com/flow2000/bing-wallpaper-api/blob/master/data/zh-CN_all.json

    /** 默认 GitHub 域名 */
    public static final String BING_GITHUB_JSON_DEFAULT_HOST = "https://raw.githubusercontent.com";
    /** JSON 数据文件路径（含 i18n_key 占位符） */
    public static final String BING_GITHUB_JSON_PATH = "/flow2000/bing-wallpaper-api/refs/heads/master/data/{i18n_key}.json";
    /** 完整 JSON 地址 — MirrorResolver.init() 启动时赋值 */
    public static String BING_GITHUB_JSON = BING_GITHUB_JSON_DEFAULT_HOST + BING_GITHUB_JSON_PATH;

    // acg 图片 api: 来源 https://www.loliapi.com/docs/acg/
    public static String ACG_WALLPAPER_API = "https://www.loliapi.com/acg?type=url";
}