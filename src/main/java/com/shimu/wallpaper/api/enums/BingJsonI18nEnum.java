package com.shimu.wallpaper.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 对应 github 多国壁纸的文件名
 */
@Getter
@AllArgsConstructor
@ToString
public enum BingJsonI18nEnum {

    de_DE("de-DE_all", "de", "DE", "德语-德国"),
    en_CA("en-CA_all", "en", "CA", "英语-加拿大"),
    en_GB("en-GB_all", "en", "GB", "英语-英国"),
    en_IN("en-IN_all", "en", "IN", "英语-印度"),
    en_US("en-US_all", "en", "US", "英语-美国"),
    fr_FR("fr-FR_all", "fr", "FR", "法语-法国"),
    ja_JP("ja-JP_all", "ja", "JP", "日语-日本"),
    zh_CN("zh-CN_all", "zh", "CN", "中文-中国大陆"),
    ;

    private final String key;
    private final String language;
    private final String country;
    private final String desc;
}
