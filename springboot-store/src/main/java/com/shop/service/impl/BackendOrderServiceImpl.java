package com.shop.service.impl;

import com.shop.mapper.BackendOrderMapper;
import com.shop.pojo.Order;
import com.shop.service.BackendOrderService;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
