package com.shop.service.impl;

import com.shop.mapper.OrderMapper;
import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.service.OrderService;
import com.shop.utils.JwtProvider;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.ceil;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    private JwtProvider jwtProvider;
    OrderServiceImpl(OrderMapper orderMapper, JwtProvider jwtProvider){
        this.orderMapper = orderMapper;
        this.jwtProvider = jwtProvider;
    }

    public Result<Integer> newOrder(Order order) {
        orderMapper.newOrder(order);
        return Result.ok(order.getOid());
    }

    public Result newOrderItems(List<OrderItems> orderItems) {
        orderMapper.newOrderItems(orderItems);
        return Result.ok(null);
    }

    public Result readOrder(String token){
        Long uid = jwtProvider.getUserId(token);
        List<Order> orders = orderMapper.readOrder(uid);
        return Result.ok(orders);
    }
    public Result getOrderDetail(Integer oid){
        List<OrderItems> data = orderMapper.getOrderDetail(oid);
        return Result.ok(data);
    }
    public Result getOrderCount(Integer pageSize, String token){
        Long uid = jwtProvider.getUserId(token);
        Integer count = orderMapper.getOrderCount(uid);
        return Result.ok(ceil(Double.valueOf(count)/pageSize));
    }
}
