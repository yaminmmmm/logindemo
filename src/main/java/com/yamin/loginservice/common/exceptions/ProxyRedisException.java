package com.yamin.loginservice.common.exceptions;

import com.yamin.loginservice.common.domain.ErrorCode;

public class ProxyRedisException extends Exception {

    public ProxyRedisException(String msg) {
        super(msg);
    }

    public ProxyRedisException() {
        super(ErrorCode.ACCESS_ERROR.getMessage());
    }
}
