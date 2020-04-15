package com.yamin.loginservice.controller;

import com.yamin.loginservice.common.domain.ApiResult;
import com.yamin.loginservice.common.domain.ApiResultCode;
import com.yamin.loginservice.orm.dto.UserDto;
import com.yamin.loginservice.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody UserDto userDto){
        if (userDto.getPassword() == null && userDto.getUsername() == null){
            return new ApiResult(ApiResultCode.LOGIN_ERROR);
        }
        ApiResult result = commonService.loginByPassword(userDto);
        return result;

    }
}
