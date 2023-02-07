package com.vinspier.plus.dal.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 前后类目关联商品映射
 * </p>
 *
 * @author xiaobiao.fan
 * @since 2022-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CmCategoryItem对象", description="前后类目关联商品映射")
public class CmCategoryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "前台三级类目ID")
    private Long categoryId;

    @ApiModelProperty(value = "站点code")
    private String websiteCode;

    @ApiModelProperty(value = "国家code")
    private String countryCode;

    @ApiModelProperty(value = "商品ID")
    private Long itemId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @Version
    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "是否删除 0否 1是")
    private Boolean deleted;


}
