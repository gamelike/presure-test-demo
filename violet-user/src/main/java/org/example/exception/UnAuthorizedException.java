package org.example.exception;

import org.example.exception.model.ResponseCode;
import org.example.exception.model.ResponseHandler;

/**
 * unauthorized exception when login failed.
 *
 * @author violet
 * @since 2022/8/31
 */
public class UnAuthorizedException extends RuntimeException implements ResponseHandler{

    private final ResponseHandler responseHandler;

    public UnAuthorizedException() {
        super();
        responseHandler = ResponseCode.unauthorizedException;
    }

    public UnAuthorizedException(String message) {
        super(message);
        responseHandler = ResponseCode.unauthorizedException;
    }

    @Override
    public String getCode() {
        return this.responseHandler.getCode();
    }

    @Override
    public String getCustomMessage() {
        return this.responseHandler.getCustomMessage();
    }
}
