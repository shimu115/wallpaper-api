package com.shimu.ramdomimg.model.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bing_wallpaper")
public class BingWallpaperPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String url;
    private String i18nKey;
    private Date dateTime;
    private String copyright;
    private String copyrightLink;
    private String hsh;
    private Date createdTime;
}
