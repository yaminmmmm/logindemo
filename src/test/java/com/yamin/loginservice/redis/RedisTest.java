package com.yamin.loginservice.redis;

import com.yamin.loginservice.common.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest(){
//        redisUtil.getOpsValue();
        Set keys = redisUtil.getRedisTemplate().keys("*");
        for (Object key : keys) {
            System.out.println(key);
        }

    }
}
