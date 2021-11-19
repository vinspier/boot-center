package com.vinspier.search.model.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Set;

/**
 * 主档商品
 * */
@Data
@TableName("good_skus")
public class GoodSkusParam {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Set<Integer> ids;

    private Integer goodId;

    private Set<Integer> goodIds;

    private Integer goodStock;

}
