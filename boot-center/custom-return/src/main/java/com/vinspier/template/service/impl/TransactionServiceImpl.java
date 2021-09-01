package com.vinspier.template.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vinspier.template.pojo.User;
import com.vinspier.template.service.TransactionService;
import com.vinspier.template.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * 事物的传播特性 验证
 * 
 * @author  vinspier
 * @date    2021/9/1 4:47 下午
 * @version 1.0
 * @menu    
*/

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    
    @Autowired
    private UserService userService;

    /**
     * 内部抛出 检查异常
     *
     * 原因：spring默认只处理运行时异常回滚
     * 结果：事物失效数据插入成功
     *
     * 默认数据库事物管理器 会对sql异常进行转换
     * 对 SQLException 包装成 TransactionSystemException（运行时异常）
     * */
    @Override
    @Transactional
    public void doWithinCheckedEpThrow() throws Exception {
        // 随机产生一个用户
        User user = generateUser();
        userService.save(user);
        log.info("insert user   " + JSONObject.toJSONString(user));
        // 1、模拟抛出检查异常
        // 2、验证 第一个插入是否生效
        log.info("模拟抛出sql检查异常错误");
        throw new SQLException("模拟抛出sql检查异常错误");
    }

    /**
     * 内部抛出 运行时异常
     *
     * 原因：spring默认只处理运行时异常回滚
     * 结果：事物生效 回滚 数据插入失效
     *
     * 默认数据库事物管理器 会对sql异常进行转换
     * 对 SQLException 包装成 TransactionSystemException（运行时异常）
     * TransactionSystemException 继承 RuntimeException
     * */
    @Override
    @Transactional
    public void doWithinUnCheckedEpThrow() {
        // 随机产生一个用户
        User user = generateUser();
        userService.save(user);
        log.info("insert user   " + JSONObject.toJSONString(user));
        // 1、模拟抛出运行时异常
        // 2、验证 第一个插入是否生效
        log.info("模拟抛出sql异常错误，包装成运行时异常");
        throw new TransactionSystemException("模拟抛出sql异常错误，包装成运行时异常");
    }

    private User generateUser(){
        User user = new User();
        String uuid = UUID.randomUUID().toString();
        user.setUsername("name_" + uuid.substring(0,5));
        user.setPhone("17767612323");
        user.setAge(30);
        user.setToken(uuid);
        user.setPassword(uuid);
        user.setSalt(uuid);
        user.setCreated(new Date());
        return user;
    }
}
