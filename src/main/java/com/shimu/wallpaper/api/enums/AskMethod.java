package com.shimu.wallpaper.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AskMethod implements BaseEnum {

    REDIRECT("redirect"),
    STREAM("stream"),
    JSON("json")
    ;

    private final String value;

}
