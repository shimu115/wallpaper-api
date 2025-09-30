package com.shimu.ramdomimg.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author shimu
 * @version 1.0
 * @description TODO
 * @date 2024-03-31 12:42
 */
//@WebFilter(urlPatterns = {"/api/*"}, filterName = "testFilter")
@Slf4j
@Component
@WebFilter
public class WebPathFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(request, servletResponse);
        long endTime = System.currentTimeMillis();
        String uri = request.getRequestURI();
        if (!StringUtils.contains(uri, "webjars") || StringUtils.contains(uri, "swagger") || StringUtils.contains(uri, "doc.html") || StringUtils.contains(uri, "v2/api")) {
            log.info("请求接口：{} =======> 请求耗时：{}ms", request.getRequestURI(), endTime - startTime);
        }
    }
}
