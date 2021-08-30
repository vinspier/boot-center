package com.vinspier.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vinspier.template.pojo.User;

/**
 * @ClassName: UserService
 * @Description: 用户相关业务逻辑接口
 * @Author:
 * @Date: 2020/3/19 11:38
 * @Version V1.0
 **/
public interface UserService extends IService<User> {


    boolean login(String username,String password,String token);
    User queryByExample(String property, Object var);
    User getByUsername(String username);

}
