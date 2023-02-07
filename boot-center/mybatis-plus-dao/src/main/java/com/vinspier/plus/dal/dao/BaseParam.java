package com.vinspier.plus.dal.dao;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 查询模型基础信息
 * */
@Data
@NoArgsConstructor
@SuperBuilder
public class BaseParam<T> {

    private Integer page = 1;

    private Integer pageSize = 50;

    /**
     * 主键ID
     * */
    private Long id;

    /**
     * 主键idSet
     * */
    private Set<Long> idSet;

    /**
     * 主键ID
     * */
    private Long beginId;

    /**
     * 主键ID
     * */
    private Long endId;

    /**
     * 状态 1启用 2禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAtStart;

    /**
     * 创建时间
     */
    private Date createdAtEnd;

    /**
     * 创建时间
     */
    private LocalDateTime createdAtBegin;

    /**
     * 创建时间
     */
    private LocalDateTime createdAtSuspend;

    /**
     * 版本号
     * */
    private Long version;

    /**
     * asc
     * 排序规则
     * */
    private List<SFunction<T,?>> ascOrders = new LinkedList<>();
    /**
     * desc
     * 排序规则
     * */
    private List<SFunction<T,?>> descOrders = new LinkedList<>();

}
