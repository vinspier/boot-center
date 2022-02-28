package com.vinspier.search.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vinspier.search.dao.OrderService;
import com.vinspier.search.mapper.OrderMapper;
import com.vinspier.search.model.po.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void insert(Order order) {
        orderMapper.insert(order);
    }

}
