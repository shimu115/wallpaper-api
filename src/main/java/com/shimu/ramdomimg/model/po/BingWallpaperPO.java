package com.shimu.ramdomimg.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "bing_wallpaper")
public class BingWallpaperPO {

    @Id
    @Column(length = 36, nullable = false, unique = true)
    private String id = UUID.randomUUID().toString();
    private String title;
    private String url;
    private String i18nKey;
    private Date dateTime;
    private String copyright;
    private String copyrightLink;
    private String hsh;
    private Date createdTime;
}
