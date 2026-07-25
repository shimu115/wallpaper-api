package com.shimu.wallpaper.api.model.response;

import com.shimu.wallpaper.api.model.vo.BIngImageVO;
import com.shimu.wallpaper.api.model.vo.BingTooltipsVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * bing 今日图片对应数据结构
 */
@Data
public class BingResponse {

    @Schema(description = "图片列表")
    private List<BIngImageVO> images;
    @Schema(description = "提示")
    private BingTooltipsVO tooltips;
}
