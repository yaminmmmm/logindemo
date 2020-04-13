package com.yamin.loginservice.dao;

import com.yamin.loginservice.orm.entity.User;
import com.yamin.loginservice.orm.mapper.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testDao(){

        User user = userDAO.selectByUsername("yamin");
        System.out.println(user);
    }
}
