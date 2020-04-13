package com.yamin.loginservice.common.domain;

public enum  ErrorCode implements IApiResultCode {
    ACCESS_ERROR(10001,"访问校验异常");

    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
