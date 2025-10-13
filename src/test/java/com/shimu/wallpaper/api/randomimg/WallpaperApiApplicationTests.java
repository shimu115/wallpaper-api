package com.shimu.wallpaper.api.randomimg;

import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.model.po.BingWallpaperPO;
import com.shimu.wallpaper.api.repository.BingWallpaperRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WallpaperApiApplicationTests {
    @Autowired
    private BingWallpaperRepository repository;

    @Test
    void contextLoads() {
        List<BingWallpaperPO> all = repository.findAll();
        ArrayList<String> arrayList = new ArrayList<>();
        for (BingWallpaperPO wallpaperPO : all) {
            String url = wallpaperPO.getUrl();
            if (StringUtils.contains(url, "1920x1080")) {
                arrayList.add(url);
            }
        }
        System.out.println(JSON.toJSONString(arrayList, true));
        System.out.println(arrayList.size());
    }

}
