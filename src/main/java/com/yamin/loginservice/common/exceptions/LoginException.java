package com.yamin.loginservice.common.exceptions;

import com.yamin.loginservice.common.domain.ApiResultCode;

public class LoginException extends RuntimeException {


    public LoginException(String message) {
        super(message);
    }

    public LoginException(ApiResultCode apiResultCode) {
        super(apiResultCode.getMessage());
    }

}
