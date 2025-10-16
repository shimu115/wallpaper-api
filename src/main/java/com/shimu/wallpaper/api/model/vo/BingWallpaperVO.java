package com.shimu.wallpaper.api.model.vo;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String id;
    private Integer dataId;
    private String title;
    private List<String> urlList;
    private String i18nKey;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dateTime;
    private String copyright;
    private String copyrightLink;
    private String hsh;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime;
}
