package com.shop.service.impl;

import com.shop.mapper.BackendProductMapper;
import com.shop.pojo.Product;
import com.shop.service.BackendProductService;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.ceil;

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
    public Result backendProductPage(Integer pageSize){
        Integer count = backendProductMapper.backendProductCount();
        return Result.ok(ceil(Double.valueOf(count)/pageSize));
    }
    public Result backendProductEdit(Product product){
        backendProductMapper.backendProductEdit(product);
        return Result.ok(null);
    }
    public Result backendProductDel(Integer id){
        backendProductMapper.backendProductDel(id);
        return Result.ok(null);
    }
}
