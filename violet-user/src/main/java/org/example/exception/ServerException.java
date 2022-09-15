package org.example.exception;

import org.example.exception.model.ResponseCode;
import org.example.exception.model.ResponseHandler;

/**
 * a general exception when server runtime exception.
 *
 * @author violet
 */
public class ServerException extends RuntimeException implements ResponseHandler {
    private final ResponseHandler handler;

    public ServerException() {
        this.handler = ResponseCode.serverException;
    }

    public ServerException(String message) {
        super(message);
        this.handler = ResponseCode.serverException;
    }

    @Override
    public String getCode() {
        return this.handler.getCode();
    }

    @Override
    public String getCustomMessage() {
        return this.handler.getCustomMessage();
    }
}
