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
@TableName("good_skus")
public class GoodSkus {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goodId;

    private Integer goodStock;

}
