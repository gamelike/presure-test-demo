package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.model.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ArgsException.class)
    public <T> ResponseResult<T> failArgsException(ArgsException e) {
        log.error("参数校验异常!", e);
        return ResponseResult.fail(e.getCode(), e.getCustomMessage());
    }

    @ExceptionHandler(ServerException.class)
    public <T> ResponseResult<T> failServerException(ServerException e) {
        log.error("服务器内部错误!", e);
        return ResponseResult.fail(e.getCode(), e.getCustomMessage());
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public void failUnAuthorizedException(UnAuthorizedException e, HttpServletResponse response) {
        log.error("未授权!", e);
        try {
            // TODO: FIXME 重定向到登录界面.
            response.sendRedirect("community/user/login");
        } catch (IOException ex) {
            throw new UnAuthorizedException(ex.getMessage());
        }
    }

}
