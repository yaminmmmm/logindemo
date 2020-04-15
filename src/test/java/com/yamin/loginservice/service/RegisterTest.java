package com.yamin.loginservice.service;

import com.yamin.loginservice.common.domain.ApiResult;
import com.yamin.loginservice.orm.dto.UserDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterTest {

    @Autowired
    private CommonService commonService;

    @Test
    @Ignore
    public void register(){
        UserDto userDto = new UserDto();
        userDto.setPassword("yamin");
        userDto.setUsername("li4xiao");
        userDto.setPhoneNumber("12102930912");
        ApiResult register = commonService.register(userDto);
        System.out.println(register.getMessage());
    }

}
