package com.shop.service.impl;

import com.shop.mapper.OrderMapper;
import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.service.OrderService;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    OrderServiceImpl(OrderMapper orderMapper){
        this.orderMapper = orderMapper;
    }

    public Result<Integer> newOrder(Order order) {
        orderMapper.newOrder(order);
        return Result.ok(order.getOid());
    }

    public Result newOrderItems(List<OrderItems> orderItems) {
        orderMapper.newOrderItems(orderItems);
        return Result.ok(null);
    }
}
