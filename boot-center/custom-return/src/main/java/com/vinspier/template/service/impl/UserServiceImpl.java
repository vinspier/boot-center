package com.vinspier.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vinspier.template.mapper.UserMapper;
import com.vinspier.template.pojo.User;
import com.vinspier.template.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户相关业务逻辑实现类
 * @Author:
 * @Date: 2020/3/19 11:38
 * @Version V1.0
 **/

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public boolean login(String username, String password, String token) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.lambda()
                .eq(User::getUsername,username)
                .eq(User::getPassword,password);

        User user = super.getOne(wrapper);

        return Objects.nonNull(user) && Objects.equals(token,user.getToken());
    }

    @Override
    public User queryByExample(String property, Object var) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq(property,var);

        return super.getOne(wrapper);
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.lambda()
                .eq(User::getUsername,username);

        return super.getOne(wrapper);
    }
}
