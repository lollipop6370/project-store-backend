package com.shop.service;

import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.utils.Result;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface OrderService {
    Result newOrder(Order order);
    Result newOrderItems(List<OrderItems> orderItems);
    MultiValueMap createPayment(Order order);
    Result readOrder(Integer currentPage, Integer pageSize, String token);
    Result getOrderDetail(Integer oid);
    Result getOrderPageCount(Integer pageSize, String token);
    Result delNoPayOrder(Integer oid);
    String payFeedBack(String tradeInfo);
}
