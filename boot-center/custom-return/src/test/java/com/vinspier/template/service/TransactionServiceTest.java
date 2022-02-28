package com.vinspier.template.service;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.vinspier.template.Application;
import com.vinspier.template.mapper.UserMapper;
import com.vinspier.template.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = {Application.class})
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void doWithinCheckedEpThrow() throws Exception{
        transactionService.doWithinCheckedEpThrow();
    }

    @Test
    public void doWithinUnCheckedEpThrow() {
        transactionService.doWithinUnCheckedEpThrow();
    }

    @Test
    public void testMapper() {
        User user = new User();
        user.setUsername("fxb");
        user.setAge(20);
        user.setPhone("12344545623");
        user.setToken("2sdf32432");
        user.setSalt("sadf32451");
        user.setPassword("235647573");
        user.setId(100L);
        user.setCreated(new Date());

        userMapper.insert(user);
        userService.saveOrUpdate(user,new UpdateWrapper<>());

    }

}