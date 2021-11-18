package com.vinspier.search.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vinspier.search.dao.GoodsService;
import com.vinspier.search.mapper.GoodsMapper;
import com.vinspier.search.model.po.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 主档商品的核心服务
 *
 * @author  xiaobiao.fan
 * @date    2021/11/17 6:31 下午
*/
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
