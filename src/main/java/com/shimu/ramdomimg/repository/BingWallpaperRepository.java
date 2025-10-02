package com.shimu.ramdomimg.repository;

import com.shimu.ramdomimg.model.po.BingWallpaperPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BingWallpaperRepository extends JpaRepository<BingWallpaperPO, Long> {

    void deleteByI18nKey(String i18nKey);

    List<BingWallpaperPO> findByI18nKey(String i18nKey);
}
