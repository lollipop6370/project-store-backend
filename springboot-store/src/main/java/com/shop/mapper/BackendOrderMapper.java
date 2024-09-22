package com.shop.mapper;

import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackendOrderMapper {
    List<Order> getBEOrder(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
    List<OrderItems> getBEOrderItems(@Param("oid")Integer oid);
    int editBEOrderStatus(@Param("oid")Integer oid, @Param("status")Integer status);
    Integer getBEOrderCount();
    int backendDelOrder(@Param("oid") Integer oid);
}
