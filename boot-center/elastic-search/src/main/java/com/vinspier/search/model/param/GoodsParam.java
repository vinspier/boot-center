package com.vinspier.search.model.param;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsParam {

    private Integer id;

    private String name;

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
