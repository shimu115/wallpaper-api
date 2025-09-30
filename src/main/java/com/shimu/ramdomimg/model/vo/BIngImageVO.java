package com.shimu.ramdomimg.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class BIngImageVO {

    private String startdate;
    private String fullstartdate;
    private String enddate;
    private String url;
    private String urlbase;
    private String copyright;
    private String copyrightlink;
    private String title;
    private String quiz;
    private boolean wp;
    private String hsh;
    private int drk;
    private int top;
    private int bot;
    private List<String> hs;
}
