package com.yamin.loginservice.service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yamin.loginservice.common.domain.ApiResult;
import com.yamin.loginservice.common.domain.ApiResultCode;
import com.yamin.loginservice.common.exceptions.ProxyRedisException;
import com.yamin.loginservice.common.utils.GlobalAttr;
import com.yamin.loginservice.common.utils.RedisUtil;
import com.yamin.loginservice.orm.dto.UserDto;
import com.yamin.loginservice.orm.entity.User;
import com.yamin.loginservice.orm.mapper.UserDAO;
import com.yamin.loginservice.service.CommonService;
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

        //todo 添加 shiro 解密
        User user = userDAO.selectByUsername(userDto.getUsername());
        if (user != null && userDto.getPassword().equals(user.getPassword())) {
            //创建唯一UUID作为token保存到缓存中
            String uuid = UUID.randomUUID().toString();
            String token = user.getUsername() + ":" + uuid;
            try {

                boolean result = redisUtil.set(token, user, GlobalAttr.EXPIRE_TIME.ONE_WEEK);
                if (result) {
                    return apiResult.set("token", token);
                } else {
                    return apiResult.setCode(ApiResultCode.SYSTEM_ERROR.getCode()).setMessage(ApiResultCode.SYSTEM_ERROR.getMessage());
                }
            } catch (ProxyRedisException e) {
                e.printStackTrace();
            }

        }
        return apiResult.setCodeAndMessage(ApiResultCode.LOGIN_FAIL);

    }
}
