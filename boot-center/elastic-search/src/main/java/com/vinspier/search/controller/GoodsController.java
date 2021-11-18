package com.vinspier.search.controller;


import com.vinspier.search.common.ResponseTemplate;
import com.vinspier.search.model.param.GoodsParam;
import com.vinspier.search.model.po.Goods;
import com.vinspier.search.service.GoodsBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
@Api(value = "主档商品模块",tags = "主档商品模块")
public class GoodsController {

    @Autowired
    private GoodsBiz goodsBiz;

    @PostMapping(value = "/list")
    @ApiOperation("商品列表")
    public ResponseTemplate<List<Goods>> list(@RequestBody GoodsParam goodsParam){
        return ResponseTemplate.ok(goodsBiz.queryList(goodsParam));
    }

}
