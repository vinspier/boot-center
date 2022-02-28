package com.vinspier.search.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vinspier.search.model.param.UserParam;
import com.vinspier.search.model.po.Order;
import com.vinspier.search.model.po.User;

import java.util.List;

public interface OrderService extends IService<Order> {

    void insert(Order order);

}
