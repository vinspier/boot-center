package com.vinspier.search.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vinspier.search.mapper.UserMapper;
import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.model.po.User;
import com.vinspier.search.service.UserSearchBiz;
import com.vinspier.search.dao.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserSearchBiz userSearchBiz;

    @Override
    public List<User> queryList(UserParam param) {
        QueryWrapper<User> wrapper = buildCommonWrapper(param);

        return super.list(wrapper);
    }


    private QueryWrapper<User> buildCommonWrapper(UserParam param){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(param.getId()),User::getId,param.getId())
                .eq(Objects.nonNull(param.getAge()),User::getAge,param.getAge())
                .like(StringUtils.hasText(param.getUsername()),User::getUsername,param.getUsername())
                .eq(StringUtils.hasText(param.getPhone()),User::getPhone,param.getPhone());

        return wrapper;
    }
}
