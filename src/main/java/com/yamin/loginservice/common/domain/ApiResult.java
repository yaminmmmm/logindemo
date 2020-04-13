package com.yamin.loginservice.common.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yamin
 * @Description 返回值模型
 */
public class ApiResult {

    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private Map<String, Object> data = new HashMap<>();

    public static ApiResult builder() {
        return new ApiResult();
    }

    public ApiResult() {
        this.code = ApiResultCode.LOGIN_SUCCESS.getCode();
        this.message = ApiResultCode.LOGIN_SUCCESS.getMessage();
    }

    public ApiResult(ApiResultCode apiResultCode) {
        this.code = apiResultCode.getCode();
        this.message = apiResultCode.getMessage();
    }

    public ApiResult(ApiResultCode apiResultCode, String message) {
        this.code = apiResultCode.getCode();
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public ApiResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ApiResult setData(Map<String, Object> data) {
        this.setData(data,true);
        return this;
    }

    public ApiResult setData(Map<String, Object> data, boolean append) {
        if (data == null) {
            return this;
        }
        if (append) {
            this.data.putAll(data);
        } else {
            this.data = data;
        }
        return this;
    }

    public ApiResult set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ApiResult setCodeAndMessage(ApiResultCode apiResultCode) {
        this.code = apiResultCode.getCode();
        this.message = apiResultCode.getMessage();
        return this;
    }
}
