package com.yamin.loginservice.controller;

import com.yamin.loginservice.common.domain.ApiResult;
import com.yamin.loginservice.common.domain.ApiResultCode;
import com.yamin.loginservice.orm.dto.UserDto;
import com.yamin.loginservice.orm.entity.User;
import com.yamin.loginservice.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    private CommonService commonService;


    @ApiOperation(value = "用户登录操作",tags = "登录接口")
    @PostMapping("/login")
    public ApiResult login(@RequestBody UserDto userDto){
        if (userDto.getPassword() == null && userDto.getUsername() == null){
            return new ApiResult(ApiResultCode.LOGIN_ERROR);
        }
        ApiResult result = commonService.loginByPassword(userDto);
        return result;
    }

    @ApiOperation(value = "用户注册操作",tags = "注册接口")
    @PostMapping("/register")
    public ApiResult register(@RequestBody UserDto userDto){
        if (userDto.getPassword() == null && userDto.getUsername() == null){
            return new ApiResult(ApiResultCode.REGISTER_FAIL);
        }
        return commonService.register(userDto);
    }


    @ApiOperation(value = "用户退出登录操作",tags = "退出接口")
    @GetMapping("/logout")
    public ApiResult logout(@RequestHeader("AUTH-TOKEN") String authToken){
        return commonService.logout(authToken);
    }
}
