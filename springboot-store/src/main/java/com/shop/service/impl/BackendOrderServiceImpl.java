package com.shop.service.impl;

import com.shop.mapper.BackendOrderMapper;
import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.service.BackendOrderService;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.ceil;

@Service
public class BackendOrderServiceImpl implements BackendOrderService {
    private BackendOrderMapper backendOrderMapper;
    BackendOrderServiceImpl(BackendOrderMapper backendOrderMapper){
        this.backendOrderMapper = backendOrderMapper;
    }

    public Result getBEOrder(Integer currentPage, Integer pageSize){
        Integer offset = (currentPage - 1) * pageSize;
        List<Order> data = backendOrderMapper.getBEOrder(offset,pageSize);
        return Result.ok(data);
    }
    public Result getBEOrderItems(Integer oid){
        List<OrderItems> data = backendOrderMapper.getBEOrderItems(oid);
        return Result.ok(data);
    }
    public Result editBEOrderStatus(Order order){
        backendOrderMapper.editBEOrderStatus(order.getOid(),order.getStatus());
        return Result.ok(null);
    }
    public Result getBEOrderCount(Integer pageSize){
        Integer totalCount = backendOrderMapper.getBEOrderCount();
        return Result.ok(ceil(Double.valueOf(totalCount) / pageSize));
    }
    public Result backendDelOrder(Integer oid){
        backendOrderMapper.backendDelOrder(oid);
        return Result.ok(null);
    }
}
