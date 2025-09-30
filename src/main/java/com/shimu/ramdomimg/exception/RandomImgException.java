package com.shimu.ramdomimg.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author shimu
 * @version 1.0
 * @description TODO
 * @date 2024-03-31 12:24
 */
@Data
public class RandomImgException extends RuntimeException {

    private Object requestParam;
    private String message;
    private Integer code;

    public RandomImgException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public RandomImgException(Throwable cause) {
        super(cause);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = cause.getMessage();
    }

    public RandomImgException(String message, Object requestParam) {
        super(message);
        this.requestParam = requestParam;
        this.message = message;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public RandomImgException(String message, Integer code, Object requestParam) {
        super(message);
        this.code = code;
        this.requestParam = requestParam;
    }
}
