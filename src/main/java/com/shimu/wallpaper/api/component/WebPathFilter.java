package com.shimu.wallpaper.api.component;

import cn.hutool.core.collection.ListUtil;
import com.shimu.wallpaper.api.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author shimu
 * @version 1.0
 */
@Slf4j
@Component
@WebFilter
public class WebPathFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        List<String> list = ListUtil.of("favicon.ico", "swagger-resources", "api-docs", "doc.html", "webjars");
        if (!isExclude(uri, list)) {
            log.info("<==================== 请求ip: {} ====================>", IpUtils.getClientIp(request));
        }
        filterChain.doFilter(request, servletResponse);
        long endTime = System.currentTimeMillis();
        if (!isExclude(uri, list)) {
            log.info("请求接口：{} =======> 请求耗时：{}ms", request.getRequestURI(), endTime - startTime);
        }
    }

    // 排除 knife4j 访问日志
    private Boolean isExclude(String uri, List<String> excludes) {
        return excludes.stream().anyMatch(exclude -> StringUtils.contains(uri, exclude));
    }
}
