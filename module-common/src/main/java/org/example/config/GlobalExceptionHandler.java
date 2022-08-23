package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.ArgsException;
import org.example.exception.model.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ArgsException.class)
    public <T> ResponseResult<T> failArgsException(ArgsException e) {
        log.error("参数校验异常!", e);
        return ResponseResult.fail(e.getCode(), e.getMessage());
    }

}
