package com.vinspier.search.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.model.po.User;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 查询用户列表
     * */
    List<User> queryList(UserParam param);

}
