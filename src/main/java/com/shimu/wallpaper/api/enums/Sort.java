package com.shimu.wallpaper.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sort implements BaseEnum<Integer> {

    DESC(0),
    ASC(1)
    ;

    private final Integer value;
}
