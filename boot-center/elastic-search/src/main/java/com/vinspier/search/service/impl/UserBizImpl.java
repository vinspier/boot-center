package com.vinspier.search.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.vinspier.search.convert.UserConvert;
import com.vinspier.search.dao.UserService;
import com.vinspier.search.model.doc.UserDoc;
import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.model.po.User;
import com.vinspier.search.service.UserBiz;
import com.vinspier.search.service.UserSearchBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserBizImpl implements UserBiz {

    @Autowired
    private UserSearchBiz userSearchBiz;

    @Autowired
    private UserService userService;

    @Override
    public List<User> queryList(UserParam param) {
        // query user Id form es
        List<UserDoc> userDocList = userSearchBiz.findList(param);
        if (CollectionUtils.isEmpty(userDocList)){
            return Lists.newArrayList();
        }

        // todo query other user info form db
        return UserConvert.convertDocToPoList(userDocList);
    }

}
