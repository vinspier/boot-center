package com.vinspier.search.controller;

import com.vinspier.search.common.ResponseTemplate;
import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.model.po.User;
import com.vinspier.search.service.UserBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户模块",tags = "用户模块")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @PostMapping(value = "/list")
    @ApiOperation("用户列表")
    public ResponseTemplate<List<User>> list(@RequestBody UserParam param){
        return ResponseTemplate.ok(userBiz.queryList(param));
    }

}
