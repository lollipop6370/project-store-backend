package com.shop.service;

import com.shop.pojo.Order;
import com.shop.utils.Result;

public interface BackendOrderService {
    Result getBEOrder(Integer currentPage, Integer pageSize);
    Result getBEOrderItems(Integer oid);
    Result editBEOrderStatus(Order order);
    Result getBEOrderCount(Integer pageSize);
    Result backendDelOrder(Integer oid);
}
