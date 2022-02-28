package com.vinspier.search.dao;

import com.vinspier.search.model.po.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void emptyId() {
        List<Order> orderList = new ArrayList<>();
        for (int i = 7; i < 10;i++) {
            Order order = new Order();
            order.setId(i);
            order.setNo("" + i);
            orderList.add(order);
        }
        Order order = new Order();
        order.setNo("100");
        orderList.add(order);
        orderService.saveOrUpdateBatch(orderList);
//        log.info("----> id = [{}]",order.getId());
    }

}