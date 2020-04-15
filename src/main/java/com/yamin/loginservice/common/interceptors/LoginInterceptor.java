package com.yamin.loginservice.common.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yamin.loginservice.common.domain.ApiResultCode;
import com.yamin.loginservice.common.exceptions.LoginException;
import com.yamin.loginservice.common.utils.RedisUtil;
import com.yamin.loginservice.orm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = getToken(request);
        try{

            Object userObject = redisUtil.get(authToken);
            if (userObject == null) {
                throw new LoginException(ApiResultCode.LOGIN_EXPIRED);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(objectMapper.writeValueAsString(userObject), User.class);
        } catch (Exception e) {
            throw new LoginException("权限发生异常"+e.getMessage());
        }


        return false;

    }

    private String getToken(HttpServletRequest request) {
        String authToken = request.getHeader("AUTH-TOKEN");
        if (authToken == null && authToken.equals("")){
            throw new LoginException("登录已失效");
        }
        return authToken;

    }


}
