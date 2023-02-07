package com.vinspier.plus.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vinspier.plus.dal.entity.CmCategoryItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 前后类目关联商品映射 Mapper 接口
 * </p>
 *
 * @author xiaobiao.fan
 * @since 2022-03-18
 */
@Mapper
public interface CmCategoryItemMapper extends BaseMapper<CmCategoryItem> {

}
