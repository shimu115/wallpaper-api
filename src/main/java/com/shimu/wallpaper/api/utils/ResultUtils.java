package com.shimu.wallpaper.api.utils;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * <p> 项目统一返回格式 </p>
 * @author shimu
 * @version 1.0
 * @date 2024-03-28 21:14
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ResultUtils<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> ResultUtils<T> success(T data) {
        return ResultUtils.<T>builder().code(HttpStatus.OK.value()).msg(null).data(data).build();
    }

    public static <T> ResultUtils<T> success() {
        return ResultUtils.<T>builder().code(HttpStatus.OK.value()).msg(null).data(null).build();
    }

    public static <T> ResultUtils<T> error(String msg, Integer code) {
        return ResultUtils.<T>builder().code(code).msg(msg).data(null).build();
    }

    public static <T> ResultUtils<T> error(String msg) {
        return ResultUtils.<T>builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(msg).data(null).build();
    }

}
