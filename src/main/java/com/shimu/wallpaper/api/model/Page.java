package com.shimu.wallpaper.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 * @param <T>
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Builder
public class Page<T> {

    @Schema(description = "当前页")
    private int page;
    @Schema(description = "每页大小")
    private int pageSize;
    @Schema(description = "总记录数")
    private long total;
    @Schema(description = "总页数")
    private int pageCount;
    @Schema(description = "当前页数据")
    private List<T> records;
    @Schema(description = "是否有下一页")
    private boolean hasNext;
    @Schema(description = "是否有上一页")
    private boolean hasPrev;
    @Schema(description = "是否是第一页")
    private boolean firstPage;
    @Schema(description = "是否是最后一页")
    private boolean lastPage;
    @Schema(description = "下一页页码")
    private Integer nextPage;
    @Schema(description = "上一页页码")
    private Integer prevPage;
    @Schema(description = "额外扩展信息")
    private Map<String, Object> extra;

}

