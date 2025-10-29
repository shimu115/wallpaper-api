package com.shimu.wallpaper.api.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BIngImageVO {

    @Schema(description = "开始时间")
    private String startDate;
    @Schema(description = "完整开始时间")
    private String fullStartDate;
    @Schema(description = "结束时间")
    private String endDate;
    @Schema(description = "图片地址")
    private String url;
    @Schema(description = "图片基础地址")
    private String urlBase;
    @Schema(description = "版权")
    private String copyright;
    @Schema(description = "版权链接")
    private String copyrightLink;
    @Schema(description = "标题")
    private String title;
    private String quiz;
    private boolean wp;
    private String hsh;
    private int drk;
    private int top;
    private int bot;
    private List<String> hs;
}
