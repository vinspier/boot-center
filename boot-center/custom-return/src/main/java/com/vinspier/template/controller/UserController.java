package com.vinspier.template.controller;

import com.vinspier.template.common.ResponseTemplate;
import com.vinspier.template.common.ResultCode;
import com.vinspier.template.exception.CustomizeException;
import com.vinspier.template.pojo.User;
import com.vinspier.template.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户模块
 *
 * @author  vinspier
 * @date    2021/8/27 6:35 下午
 * @version 1.0
 * @menu
*/
@RestController
@RequestMapping(value = "user")
@Api(value = "用户模块",tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getById/{id}")
    @ApiOperation("通过ID获取信息")
    public ResponseTemplate<User> getById(@PathVariable Long id){
        User user = userService.getById(id);
        return ResponseTemplate.ok(user);
    }

    @GetMapping("queryByProperty/{property}/{value}")
    @ApiOperation("指定属性名/值查询信息")
    public ResponseTemplate<User> queryByProperty(@PathVariable String property,@PathVariable Object value){
        User user = userService.queryByExample(property,value);
        return ResponseTemplate.ok(user);
    }

    @GetMapping("getByUsername/{username}")
    @ApiOperation("通过名称查询信息")
    public ResponseTemplate<User> getByUsername(@PathVariable String username){
        User user = userService.getByUsername(username);
        return ResponseTemplate.ok(user);
    }

    @GetMapping("testExceptionHandler/{id}")
    @ApiOperation("测试统一异常处理信息")
    public ResponseTemplate<User> testExceptionHandler(@PathVariable Long id){
        User user = userService.getById(id);
        // 模拟出异常
        if (user == null){
            throw new CustomizeException(ResultCode.USER_NOT_EXIST);
        }
        return ResponseTemplate.ok(user);
    }

}
