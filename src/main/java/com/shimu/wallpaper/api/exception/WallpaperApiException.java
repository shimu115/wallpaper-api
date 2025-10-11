package com.shimu.wallpaper.api.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常
 * @author shimu
 * @version 1.0
 * @date 2024-03-31 12:24
 */
@Data
public class WallpaperApiException extends RuntimeException {

    private Object requestParam;
    private String message;
    private Integer code;

    public WallpaperApiException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public WallpaperApiException(String message) {
        super(message);
        this.message = message;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public WallpaperApiException(Throwable cause) {
        super(cause);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = cause.getMessage();
    }

    public WallpaperApiException(String message, Object requestParam) {
        super(message);
        this.requestParam = requestParam;
        this.message = message;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public WallpaperApiException(String message, Integer code, Object requestParam) {
        super(message);
        this.code = code;
        this.requestParam = requestParam;
    }
}
