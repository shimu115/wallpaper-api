package com.shimu.wallpaper.api.model.response;

import lombok.Data;

import java.util.List;

/**
 * GitHub json 结果
 * @param <T>
 */
@Data
public class GitHubJsonResult <T> {

    private Integer code;
    private String msg;
    private Integer total;
    private T data;
}
