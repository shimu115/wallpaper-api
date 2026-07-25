package com.shimu.wallpaper.api.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * GitHub json 结果
 * @param <T>
 */
@Data
@Schema(description = "GitHub 请求结果")
public class GitHubJsonResult <T> {

    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "提示信息")
    private String msg;
    @Schema(description = "数据总数")
    private Integer total;
    @Schema(description = "数据")
    private T data;
}
