package com.vinspier.plus.dal.param;

import com.baomidou.mybatisplus.annotation.*;
import com.vinspier.plus.dal.support.BaseParam;
import com.vinspier.plus.dal.entity.CmCategoryItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 前后类目关联商品映射
 * </p>
 *
 * @author xiaobiao.fan
 * @since 2022-03-18
 */
@Data
@SuperBuilder
public class CmCategoryItemParam extends BaseParam<CmCategoryItem> {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "前台三级类目ID")
    private Long categoryId;

    @ApiModelProperty(value = "站点code")
    private String websiteCode;

    @ApiModelProperty(value = "国家code")
    private String countryCode;

    @ApiModelProperty(value = "商品ID")
    private Long itemId;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "是否删除 0否 1是")
    private Boolean deleted;


}
