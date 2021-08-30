package com.vinspier.template.controller;

import com.vinspier.template.Application;
import com.vinspier.template.pojo.User;
import com.vinspier.template.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserControllerTest {

    @Autowired
    private UserService userService;

    private Random random = new Random();

    @Test
    public void generateUser(){
        List<User> users = new ArrayList<>();
        Date current = new Date();
        for (int i = 0; i < 12000; i++){
            for (int j = 0; j < 1000; j++){
                users.clear();
                User user = new User();
                user.setAge(random.nextInt(100));
                user.setUsername("index" + i + "name" + j);
                user.setPassword(UUID.randomUUID().toString());
                user.setSalt(UUID.randomUUID().toString());
                user.setToken(UUID.randomUUID().toString());
                user.setPhone("1776716023" + random.nextInt(10));
                user.setCreated(current);
                users.add(user);
                userService.saveBatch(users);
            }
        }
        new User();
    }

}