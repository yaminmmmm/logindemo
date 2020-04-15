package com.yamin.loginservice.common.domain;

public enum ApiResultCode implements IApiResultCode{
    LOGIN_SUCCESS(0,"登录成功"),
    LOGIN_FAIL(-10001,"登录失败,用户名或密码错误"),
    LOGIN_ERROR(-10002,"登录出错，用户名或密码不能为空"),
    LOGIN_EXPIRED(-10003,"登录验证已过期，请重新登录"),
    SYSTEM_ERROR(-40000,"系统出错，请稍后再登录");

    private Integer code;
    private String message;


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    ApiResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
