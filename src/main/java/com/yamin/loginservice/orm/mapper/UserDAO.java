package com.yamin.loginservice.orm.mapper;

import com.yamin.loginservice.orm.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * UserDAO继承基类
 */
@Repository
public interface UserDAO extends MyBatisBaseDao<User, Integer> {

    /**
     * 根据用户名获取信息
     * @param username
     * @return
     */
    User selectByUsername(String username);
}