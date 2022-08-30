package org.example.exception.model;

import lombok.Getter;

@Getter
public class ResponseResult<T> {

    private String code;
    private String message;
    private T data;

    private ResponseResult() {
    }

    private ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseCode.success.getCode(), ResponseCode.success.getCustomMessage(), data);
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseCode.success.getCode(), ResponseCode.success.getCustomMessage(), null);
    }

    public static <T> ResponseResult<T> fail(String code, String message) {
        return new ResponseResult<>(code, message, null);
    }

}
