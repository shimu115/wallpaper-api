package com.shimu.ramdomimg.utils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Builder
public class PageUtils<T> {

    private int page;           // 当前页
    private int pageSize;       // 每页大小
    private long total;         // 总记录数
    private int pageCount;      // 总页数

    private List<T> records;    // 当前页数据

    private boolean hasNext;    // 是否有下一页
    private boolean hasPrev;    // 是否有上一页
    private boolean isFirstPage;
    private boolean isLastPage;

    private Integer nextPage;   // 下一页页码
    private Integer prevPage;   // 上一页页码

//    @Singular
    private Map<String, Object> extra; // 额外扩展信息

    // 自定义构建方法
    public static <T> PageUtils<T> buildPage(int page, int pageSize, long total, List<T> records) {
        PageUtils<T> p = PageUtils.<T>builder()
                .page(Math.max(page, 1))
                .pageSize(pageSize <= 0 ? 10 : pageSize)
                .total(Math.max(total, 0))
                .records(records)
                .build();

        if (CollectionUtil.isEmpty(records)) {
            return p;
        }

        // 计算总页数
        p.pageCount = (int) Math.ceil((double) p.total / p.pageSize);

        // 边界处理
        if (p.page > p.pageCount && p.pageCount > 0) {
            p.page = p.pageCount;
        }

        // 计算前后页
        p.hasPrev = p.page > 1;
        p.hasNext = p.page < p.pageCount;
        p.isFirstPage = p.page == 1;
        p.isLastPage = p.page == p.pageCount;

        p.prevPage = p.hasPrev ? p.page - 1 : null;
        p.nextPage = p.hasNext ? p.page + 1 : null;

        return p;
    }
}

