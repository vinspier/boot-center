package com.vinspier.search.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.vinspier.search.dao.GoodsService;
import com.vinspier.search.model.doc.GoodsDoc;
import com.vinspier.search.model.param.GoodsParam;
import com.vinspier.search.model.po.Goods;
import com.vinspier.search.service.GoodsBiz;
import com.vinspier.search.service.GoodsSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsBizImpl implements GoodsBiz {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsSearch goodsSearch;

    @Override
    public List<Goods> queryList(GoodsParam param) {

        List<GoodsDoc> goodsDocList = goodsSearch.queryList(param);
        if (CollectionUtils.isEmpty(goodsDocList)){
            return Lists.newArrayList();
        }

        Set<Integer> goodsIdList = goodsDocList.stream().map(GoodsDoc::getId).collect(Collectors.toSet());
        List<Goods> goodsList = goodsService.listByIds(goodsIdList);

        return goodsList;
    }

}
