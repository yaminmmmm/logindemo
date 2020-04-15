package com.yamin.loginservice.service.impl;

import com.yamin.loginservice.common.domain.ApiResult;
import com.yamin.loginservice.common.domain.ApiResultCode;
import com.yamin.loginservice.common.exceptions.ProxyRedisException;
import com.yamin.loginservice.common.utils.RedisUtil;
import com.yamin.loginservice.orm.dto.UserDto;
import com.yamin.loginservice.orm.entity.User;
import com.yamin.loginservice.orm.mapper.UserDAO;
import com.yamin.loginservice.service.CommonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ApiResult loginByPassword(UserDto userDto) {


        ApiResult apiResult = new ApiResult();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken shiroToken = new UsernamePasswordToken(userDto.getUsername(), userDto.getPassword());
        User user = null;
        try {
            subject.login(shiroToken);
            user = userDAO.selectByUsername(userDto.getUsername());

            String uuid = UUID.randomUUID().toString();
            String loginToken = user.getUsername() + uuid;
            try {
                boolean result = redisUtil.set(loginToken, user);
                if (result) {
                    apiResult.set("token",loginToken);
                }else {
                    apiResult.setCodeAndMessage(ApiResultCode.LOGIN_FAIL);
                }
            } catch (ProxyRedisException e) {
                return new ApiResult(ApiResultCode.SYSTEM_ERROR);
            }




        } catch (AuthenticationException e) {
            return new ApiResult(ApiResultCode.LOGIN_FAIL);
        }

        return apiResult;

    }
}
