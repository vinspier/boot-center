package com.vinspier.search.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.vinspier.search.convert.UserConvert;
import com.vinspier.search.dao.UserRepository;
import com.vinspier.search.dao.UserService;
import com.vinspier.search.model.doc.UserDoc;
import com.vinspier.search.model.po.User;
import com.vinspier.search.service.SearchBiz;
import com.vinspier.search.service.UserBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SearchBizImpl implements SearchBiz {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private UserService userService;

    @Override
    public boolean initUserIndex(String indexName) {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName));
        return indexOperations.create();
    }

    @Override
    public UserDoc saveUser(Integer userId) {
        User user = userService.getById(userId);
        if (Objects.isNull(user)){
            return null;
        }
        UserDoc userDoc = UserConvert.convertPoToDoc(user);
        return userRepository.save(userDoc);
    }

    @Override
    public List<UserDoc> initUsers(List<Integer> userIds) {
        List<User> users = userService.listByIds(userIds);
        if (CollectionUtils.isEmpty(users)){
            return Lists.newArrayList();
        }
        List<UserDoc> userDocList = UserConvert.convertPoToDocList(users);
        userRepository.saveAll(userDocList);

        return userDocList;
    }
}
