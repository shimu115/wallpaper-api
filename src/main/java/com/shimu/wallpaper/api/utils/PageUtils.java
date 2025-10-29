package com.shimu.wallpaper.api.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.shimu.wallpaper.api.exception.WallpaperApiException;
import com.shimu.wallpaper.api.model.Page;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 * @param <T>
 */

public class PageUtils<T> {

    /**
     * 自定义构建方法
     * @param page
     * @param pageSize
     * @param total
     * @param records
     * @return
     * @param <T>
     */
    public static <T> Page<T> buildPage(int page, int pageSize, long total, List<T> records) {
        Page<T> p = Page.<T>builder()
                .page(Math.max(page, 1))
                .pageSize(pageSize <= 0 ? 10 : pageSize)
                .total(Math.max(total, 0))
                .records(records)
                .build();

        if (CollectionUtil.isEmpty(records)) {
            return p;
        }

        int pageCount = p.getPageCount();
        long pageTotal = p.getTotal();
        int pPage = p.getPage();
        int pPageSize = p.getPageSize();

        // 计算总页数
        p.setPageCount((int) Math.ceil((double) pageTotal / pPageSize));

        // 边界处理
        if (p.getPage() > pageCount && pageCount > 0) {
            throw new WallpaperApiException("page 超出总页数（pageCount） " + pageCount + "，请重新输入页数", 10002);
        }

        // 计算前后页
        p.setHasPrev(pPage > 1);
        p.setHasNext(pPage < pageCount);
        p.setFirstPage(pPage == 1);
        p.setLastPage(pPage == pageCount);

        p.setPrevPage(p.isHasPrev() ? pPage - 1 : null);
        p.setNextPage(p.isHasNext() ? pPage + 1 : null);

        // 计算当前页的数据
        int fromIndex = (pPage - 1) * pPageSize;
        int toIndex = Math.min(fromIndex + pPageSize, records.size());
        p.setRecords(records.subList(fromIndex, toIndex));

        return p;
    }
}

