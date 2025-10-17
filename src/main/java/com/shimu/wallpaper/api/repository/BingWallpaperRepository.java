package com.shimu.wallpaper.api.repository;

import com.shimu.wallpaper.api.model.po.BingWallpaperPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * bing 数据原备份数据库
 */
public interface BingWallpaperRepository extends JpaRepository<BingWallpaperPO, Long> {

    List<BingWallpaperPO> findByI18nKey(String i18nKey);
}
