package com.vinspier.search.service;

import com.vinspier.search.model.param.GoodsParam;
import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.model.po.Goods;
import com.vinspier.search.model.po.User;

import java.util.List;

public interface GoodsBiz {

    /**
     * 查询商品列表
     * */
    List<Goods> queryList(GoodsParam param);

}
