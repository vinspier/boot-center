package com.vinspier.search.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 主档商品
 * */
@Data
@TableName("goods")
public class Goods {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String goodsTitle;

    private Integer categoryId;

    private String categoryPath;

    private Integer supplierId;

    private Integer status;

    private Integer openingDirection;

    private Integer stockType;

    private Date createdAt;

    private Date shelfAt;

}
