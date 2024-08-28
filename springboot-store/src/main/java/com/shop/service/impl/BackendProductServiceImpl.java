package com.shop.service.impl;

import com.shop.mapper.BackendProductMapper;
import com.shop.pojo.Product;
import com.shop.service.BackendProductService;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendProductServiceImpl implements BackendProductService {

    private BackendProductMapper backendProductMapper;
    BackendProductServiceImpl(BackendProductMapper backendProductMapper){
        this.backendProductMapper = backendProductMapper;
    }
    public Result backendProduct(Integer currentPage, Integer pageSize){
        Integer offset = (currentPage - 1) * pageSize;
        List<Product> data = backendProductMapper.backendProduct(offset,pageSize);
        return Result.ok(data);
    }
}
