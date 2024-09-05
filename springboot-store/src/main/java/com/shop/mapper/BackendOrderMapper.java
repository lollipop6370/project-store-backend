package com.shop.mapper;

import com.shop.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackendOrderMapper {
    List<Order> getBEOrder(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
}
