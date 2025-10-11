package com.shimu.wallpaper.api.model.response;

import com.shimu.wallpaper.api.model.vo.BIngImageVO;
import com.shimu.wallpaper.api.model.vo.BingTooltipsVO;
import lombok.Data;

import java.util.List;

/**
 * bing 今日图片对应数据结构
 */
@Data
public class BingResponse {

    private List<BIngImageVO> images;
    private BingTooltipsVO tooltips;
}
