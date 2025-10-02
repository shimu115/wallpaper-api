package com.shimu.ramdomimg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BingJsonI18nEnum {

    de_DE("de-DE_all"),
    en_CA("en-CA_all"),
    en_GB("en-GB_all"),
    en_IN("en-IN_all"),
    en_US("en-US_all"),
    fr_FR("fr-FR_all"),
    ja_JP("ja-JP_all"),
    zh_CN("zh-CN_all"),
    ;

    private final String key;
}
