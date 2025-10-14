package com.shimu.wallpaper.api.model.po;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Table(name = "bing_daily_wallpaper")
@Entity
public class BingDailyWallpaperPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String startDate;
    private String fullStartDate;
    private String endDate;
    private String url;
    private String urlBase;
    private String copyright;
    private String copyrightLink;
    private String title;
    private String quiz;
    private boolean wp;
    private String hsh;
    private int drk;
    private int top;
    private int bot;
}
