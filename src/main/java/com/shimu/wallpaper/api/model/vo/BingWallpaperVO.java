package com.shimu.wallpaper.api.model.vo;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 数据库对应实体
 */
@Data
public class BingWallpaperVO {

    @Schema(description = "主键")
    private String id;
    @Schema(description = "数据id")
    private Integer dataId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "图片url")
    private List<String> urlList;
    @Schema(description = "图片对应语言")
    private String i18nKey;
    @Schema(description = "图片时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dateTime;
    @Schema(description = "图片版权")
    private String copyright;
    @Schema(description = "图片版权链接")
    private String copyrightLink;
    @Schema(description = "图片hash")
    private String hsh;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime;
}
