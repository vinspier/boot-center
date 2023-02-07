package com.vinspier.plus.dal.dao.cmCategory.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vinspier.plus.dal.dao.cmCategory.CmCategoryItemDao;
import com.vinspier.plus.dal.entity.CmCategoryItem;
import com.vinspier.plus.dal.mapper.CmCategoryItemMapper;
import com.vinspier.plus.dal.param.CmCategoryItemParam;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CmCategoryItemDaoImpl extends ServiceImpl<CmCategoryItemMapper, CmCategoryItem> implements CmCategoryItemDao {

    @Override
    public QueryWrapper<CmCategoryItem> buildQueryWrapper(CmCategoryItemParam param) {
        QueryWrapper<CmCategoryItem> wrapper = CmCategoryItemDao.super.buildQueryWrapper(param);
        wrapper.lambda()
                .eq(StringUtils.isNotBlank(param.getWebsiteCode()),CmCategoryItem::getWebsiteCode,param.getWebsiteCode())
                .eq(StringUtils.isNotBlank(param.getCountryCode()),CmCategoryItem::getCountryCode,param.getCountryCode())
                .eq(Objects.nonNull(param.getItemId()),CmCategoryItem::getItemId,param.getItemId())
                .eq(Objects.nonNull(param.getDeleted()),CmCategoryItem::getDeleted,param.getDeleted());
        return wrapper;
    }

    @Override
    public boolean updateLimitCondition(CmCategoryItemParam param) {
        boolean updateLimitCondition = CmCategoryItemDao.super.updateLimitCondition(param);
        return updateLimitCondition && StringUtils.isNotBlank(param.getWebsiteCode())
                && StringUtils.isNotBlank(param.getCountryCode());
    }

}
