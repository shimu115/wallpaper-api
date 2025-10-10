package com.shimu.wallpaper.api.enums;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.shimu.wallpaper.api.component.MirrorComponent;
import com.shimu.wallpaper.api.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

public class ApiContains {

    // https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN
    public static String BING_PHOTO_API = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN";
    // 文件位置：https://github.com/flow2000/bing-wallpaper-api/blob/master/data/zh-CN_all.json
    public static String BING_GITHUB_JSON = MirrorComponent.getBingGithubJson() + "/flow2000/bing-wallpaper-api/refs/heads/master/data/{i18n_key}.json";
    // acg 图片 api: 来源 https://www.loliapi.com/docs/acg/
    public static String ACG_WALLPAPER_API = "https://www.loliapi.com/acg?type=url";
}
