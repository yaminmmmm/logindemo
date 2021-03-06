package com.yamin.loginservice.service.impl;

import com.yamin.loginservice.common.domain.GlobalAttr;
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
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class CommonServiceImpl implements CommonService {

    //TODO 编写多种登录方式
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ApiResult loginByPassword(UserDto userDto) {


        ApiResult apiResult = new ApiResult();
        Subject subject = SecurityUtils.getSubject();
        //将传入的username和password生成一个token
        UsernamePasswordToken shiroToken = new UsernamePasswordToken(userDto.getUsername(), userDto.getPassword());
        User user = null;
        try {
            //将含有用户信息的token传入login方法中验证是够登录成功
            subject.login(shiroToken);
            user = userDAO.selectByUsername(userDto.getUsername());

            //生成唯一识别符与username组成登录认证令牌token
            String uuid = UUID.randomUUID().toString();
            String authToken = user.getUsername() + ":" + uuid;
            try {
                //保存登录状态到redis缓存中，失效时间为一星期
                boolean result = redisUtil.set(authToken, user, GlobalAttr.EXPIRE_TIME.ONE_WEEK);
                if (result) {
                    apiResult.set("AUTH-TOKEN", authToken);
                } else {
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

    @Override
    public ApiResult loginByPhoneNumber(UserDto userDto) {

        //todo 通过手机验证码登录
        return null;
    }

    @Override
    @Transactional
    public ApiResult register(UserDto userDto) {
        User user = new User();

        //todo 可以改造成使用redis判断用户名是否已存在
        User userIsExist = userDAO.selectByUsername(userDto.getUsername());
        if (userIsExist != null) {
            return new ApiResult(ApiResultCode.REGISTER_USERNAME_IS_EXIST);
        }
        //通过shiro使用md5加密用户密码
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithName = "md5";
        String encodePassword = new SimpleHash(algorithName, userDto.getPassword(), salt, times).toString();

        user.setPhone(userDto.getPhoneNumber());
        user.setSalt(salt);
        user.setPassword(encodePassword);
        user.setUsername(userDto.getUsername());
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        int effected = userDAO.insertSelective(user);
        if (effected <= 0) {
            return new ApiResult(ApiResultCode.SYSTEM_ERROR);
        }

        return new ApiResult();
    }

    @Override
    public ApiResult logout(String authToken) {
        if (authToken == null && authToken.equals("")) {
            return new ApiResult(ApiResultCode.LOGOUT_FAIL);
        }
        redisUtil.remove(authToken);
        return ApiResult.builder();
    }
}
