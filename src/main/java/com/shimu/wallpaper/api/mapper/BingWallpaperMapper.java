package com.shimu.wallpaper.api.mapper;

import com.shimu.wallpaper.api.model.po.BingWallpaperPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BingWallpaperMapper {

    List<BingWallpaperPO> findByI18nKey(@Param("i18nKey") String i18nKey);

    List<BingWallpaperPO> findBy(@Param("i18nKey") String i18nKey,
                                 @Param("dataId") Integer dataId,
                                 @Param("startTime") Long starTime,
                                 @Param("endTime") Long endTime);
}
