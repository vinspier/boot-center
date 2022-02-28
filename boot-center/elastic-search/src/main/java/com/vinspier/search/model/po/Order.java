package com.vinspier.search.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 订单
 * */
@Data
@TableName("`order`")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "`no`")
    private String no;

}
