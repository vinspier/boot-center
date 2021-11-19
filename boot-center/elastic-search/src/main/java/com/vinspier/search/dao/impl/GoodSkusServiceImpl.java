package com.vinspier.search.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vinspier.search.dao.GoodSkusService;
import com.vinspier.search.mapper.GoodSkusMapper;
import com.vinspier.search.model.param.GoodSkusParam;
import com.vinspier.search.model.po.GoodSkus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GoodSkusServiceImpl extends ServiceImpl<GoodSkusMapper, GoodSkus> implements GoodSkusService {

    @Override
    public List<GoodSkus> queryList(GoodSkusParam param) {
        QueryWrapper<GoodSkus> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(param.getId()),GoodSkus::getId,param.getId())
                .eq(Objects.nonNull(param.getGoodId()),GoodSkus::getGoodId,param.getGoodId())
                .in(CollectionUtils.isNotEmpty(param.getIds()),GoodSkus::getId,param.getId())
                .in(CollectionUtils.isNotEmpty(param.getGoodIds()),GoodSkus::getGoodId,param.getGoodIds());

        return super.list(wrapper);
    }


}
