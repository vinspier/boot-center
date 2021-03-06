package com.vinspier.search.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.vinspier.search.convert.GoodSkusConvert;
import com.vinspier.search.convert.GoodsConvert;
import com.vinspier.search.convert.UserConvert;
import com.vinspier.search.dao.*;
import com.vinspier.search.model.doc.GoodsDoc;
import com.vinspier.search.model.doc.UserDoc;
import com.vinspier.search.model.param.GoodSkusParam;
import com.vinspier.search.model.po.GoodSkus;
import com.vinspier.search.model.po.Goods;
import com.vinspier.search.model.po.User;
import com.vinspier.search.service.SearchBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SearchBizImpl implements SearchBiz {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodSkusService goodSkusService;

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

    @Override
    public List<GoodsDoc> saveGoodsDocs(List<Integer> goodsIds) {
        List<Goods> goodsList = goodsService.listByIds(goodsIds);
        if (CollectionUtils.isEmpty(goodsList)){
            return Lists.newArrayList();
        }
        List<GoodsDoc> goodsDocList = GoodsConvert.convertPOListToDocList(goodsList);
        Set<Integer> goodIds = goodsDocList.stream().map(GoodsDoc::getId).collect(Collectors.toSet());
        GoodSkusParam param = new GoodSkusParam();
        param.setGoodIds(goodIds);
        List<GoodSkus> skusList = goodSkusService.queryList(param);
        Map<Integer,List<GoodSkus>> skuMap = skusList.stream().collect(Collectors.groupingBy(GoodSkus::getGoodId));
        goodsDocList.forEach(item -> {
            item.setSkus(GoodSkusConvert.convertPOListToDocList(skuMap.get(item.getId())));
        });
        goodsRepository.saveAll(goodsDocList);

        return goodsDocList;
    }
}
