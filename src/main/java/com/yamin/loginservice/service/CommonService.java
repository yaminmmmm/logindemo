package com.yamin.loginservice.service;

import com.yamin.loginservice.common.domain.ApiResult;
import com.yamin.loginservice.orm.dto.UserDto;


public interface CommonService {
    ApiResult loginByPassword(UserDto userDto);

    ApiResult loginByPhoneNumber(UserDto userDto);
}
