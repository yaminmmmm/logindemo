package com.yamin.loginservice.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yamin.loginservice.common.utils.RedisUtil;
import com.yamin.loginservice.orm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redis() throws JsonProcessingException {

        Object o = redisUtil.get("yamin:51fcd678-bb36-4de5-a2f7-cb97d6bc84b9");

        System.out.println("object:"+o);
        ObjectMapper objectMapper = new ObjectMapper();

        String s = objectMapper.writeValueAsString(o);
        User user = objectMapper.readValue(s, User.class);
        System.out.println("user:"+user);
    }
}
