package com.shimu.ramdomimg.model.response;

import com.shimu.ramdomimg.model.vo.BIngImageVO;
import com.shimu.ramdomimg.model.vo.BingTooltipsVO;
import lombok.Data;

import java.util.List;

@Data
public class BingResponse {

    private List<BIngImageVO> images;
    private BingTooltipsVO tooltips;
}
