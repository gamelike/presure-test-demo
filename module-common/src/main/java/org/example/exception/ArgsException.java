package org.example.exception;

import org.example.exception.model.ResponseCode;
import org.example.exception.model.ResponseHandler;

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
    public String getMessage() {
        return code.getMessage();
    }
}
