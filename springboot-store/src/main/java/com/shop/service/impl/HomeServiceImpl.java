package com.shop.service.impl;

import com.shop.mapper.HomeMapper;
import com.shop.service.HomeService;
import com.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;
    public Result getProductCount(){
        int count = homeMapper.getProductCount();
        return Result.ok(count);
    }
}
