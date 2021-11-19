package com.vinspier.search.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vinspier.search.model.param.GoodSkusParam;
import com.vinspier.search.model.po.GoodSkus;
import com.vinspier.search.model.po.Goods;

import java.util.List;

/**
 * 
 * @author  xiaobiao.fan
 * @date    2021/11/17 8:42 下午
*/
public interface GoodSkusService extends IService<GoodSkus> {

    List<GoodSkus> queryList(GoodSkusParam param);

}
