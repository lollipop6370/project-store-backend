package com.shop.service;

import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.utils.Result;

import java.util.List;

public interface OrderService {
    Result newOrder(Order order);
    Result newOrderItems(List<OrderItems> orderItems);
}
