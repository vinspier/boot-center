package com.vinspier.search.service;

import com.vinspier.search.model.doc.GoodsDoc;
import com.vinspier.search.model.param.GoodsParam;

import java.util.List;

public interface GoodsSearch {

    /**
     * 查询商品列表
     * */
    List<GoodsDoc> queryList(GoodsParam param);

}
