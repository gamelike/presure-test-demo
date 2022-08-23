package org.example.exception.model;

public enum ResponseCode implements ResponseHandler{

    success("100", "success response!"),
    argsException("400", "bad request args."),
    serverException("500", "server exception.");


    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
