package com.shimu.wallpaper.api.model.response;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * github 数据源对应的数据结构
 */
@Data
@Schema(description = "github 数据源对应数据结构")
public class GitHubJsonResponse {

    @Schema(description = "id")
    private Integer id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "图片地址")
    private String url;
    @Schema(description = "时间")
    @JsonProperty("datetime")
    private Date dateTime;
    @Schema(description = "版权")
    private String copyright;
    @JsonProperty("copyrightlink")
    @Schema(description = "版权链接")
    private String copyrightLink;
    @Schema(description = "图片hash")
    private String hsh;
    @Schema(description = "创建时间")
    @JsonProperty("created_time")
    private Date createdTime;
}
