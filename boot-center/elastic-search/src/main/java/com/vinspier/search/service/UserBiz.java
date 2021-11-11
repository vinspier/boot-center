package com.vinspier.search.service;

import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.model.po.User;

import java.util.List;

public interface UserBiz {

    /**
     * 查询用户列表
     * */
    List<User> queryList(UserParam param);

}
