package com.shimu.wallpaper.api.model.po;

import cn.hutool.core.date.DateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * 数据库对应实体
 */
@Data
@Entity
@Table(name = "bing_wallpaper")
@Schema(description = "必应壁纸")
public class BingWallpaperPO {

    @Id
    @Column(length = 36, nullable = false, unique = true)
    @Schema(description = "uuid")
    private String id = UUID.randomUUID().toString();
    @Schema(description = "源数据数据id，对应 GitHubJsonResponse.id")
    private Integer dataId;
    @Schema(description = "壁纸标题")
    private String title;
    @Schema(description = "壁纸url")
    private String url;
    @Schema(description = "壁纸对应的语言")
    private String i18nKey;
    private Date dateTime;
    @Schema(description = "壁纸版权")
    private String copyright;
    @Schema(description = "壁纸版权链接")
    private String copyrightLink;
    @Schema(description = "壁纸hash")
    private String hsh;
    @Schema(description = "壁纸创建时间")
    private Date createdTime;
}
