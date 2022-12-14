package org.example.exception;

import org.example.exception.model.ResponseCode;
import org.example.exception.model.ResponseHandler;

/**
 * throw the exception when valid args exception.
 */
public class ArgsException extends RuntimeException implements ResponseHandler {

    private final ResponseCode code;

    public ArgsException() {
        this.code = ResponseCode.argsException;
    }

    public ArgsException(String message) {
        super(message);
        this.code = ResponseCode.argsException;
    }

    @Override
    public String getCode() {
        return code.getCode();
    }

    @Override
    public String getCustomMessage() {
        return code.getCustomMessage();
    }
}
