package com.shimu.wallpaper.api.model.response;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GitHubJsonResponse {

    private Integer id;
    private String title;
    private String url;
    @JsonProperty("datetime")
    private Date dateTime;
    private String copyright;
    @JsonProperty("copyrightlink")
    private String copyrightLink;
    private String hsh;
    @JsonProperty("created_time")
    private Date createdTime;
}
