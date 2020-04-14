package com.yamin.loginservice.controller;

import com.yamin.loginservice.common.domain.ApiResult;
import com.yamin.loginservice.common.domain.ApiResultCode;
import com.yamin.loginservice.orm.dto.UserDto;
import com.yamin.loginservice.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "登录操作",tags = "登录操作")
    @PostMapping("/login")
    public ApiResult login(@RequestBody UserDto userDto){
        if (userDto.getPassword() == null && userDto.getUsername() == null){
            return new ApiResult(ApiResultCode.LOGIN_ERROR);
        }
        ApiResult result = commonService.loginByPassword(userDto);
        return result;

    }
}
