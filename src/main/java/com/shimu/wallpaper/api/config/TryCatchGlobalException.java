package com.shimu.wallpaper.api.config;

import com.alibaba.fastjson.JSON;
import com.shimu.wallpaper.api.exception.RandomImgException;
import com.shimu.wallpaper.api.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author shimu
 * @version 1.0
 * @description TODO
 * @date 2024-04-12 11:22
 */
@RestControllerAdvice
@Slf4j
public class TryCatchGlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultUtils exceptionHandler(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String toJSONString = JSON.toJSONString(parameterMap);
        log.error("request url =====================> {}", request.getRequestURI());
        log.error("requestPrams: {}", toJSONString);
        if (StringUtils.isEmpty(e.getMessage())) {
            return ResultUtils.error(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResultUtils.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultUtils NotFoundExceptionHandler(Exception e, HttpServletRequest request) {
        log.error("unable to find this path: {} =======> status: {}", request.getRequestURI(), HttpStatus.NOT_FOUND);
        return ResultUtils.error(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(RandomImgException.class)
    public ResultUtils testExceptionHandler(RandomImgException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("请求参数：{}", JSON.toJSONString(request.getParameterMap()));
        log.error("错误信息：{}", e.getMessage());
        log.error("请求头信息：{}", JSON.toJSONString(request.getHeaderNames()));
        e.printStackTrace();
        if (StringUtils.isEmpty(e.getMessage())) {
            return ResultUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResultUtils.error(e.getMessage(), e.getCode());
    }

}
