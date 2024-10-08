package com.shop.mapper;

import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int newOrder(@Param("order")Order order);
    int newOrderItems(@Param("orderItems")List<OrderItems> orderItems);
    List<Order> readOrder(@Param("pageSize")Integer pageSize, @Param("offset") Integer offset, @Param("uid")Long uid);
    List<OrderItems> getOrderDetail(@Param("oid") Integer oid);
    Integer getOrderCount(@Param("uid") Long uid);
    int delNoPayOrder(@Param("oid") Integer oid);
    int paySuccess(@Param("oid") Integer oid);
}
