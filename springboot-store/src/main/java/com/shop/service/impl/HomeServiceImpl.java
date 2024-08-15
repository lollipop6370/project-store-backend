package com.shop.service.impl;

import com.shop.mapper.HomeMapper;
import com.shop.pojo.PageConfigItem;
import com.shop.pojo.Product;
import com.shop.service.HomeService;
import com.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;
    public Result getProductCount(){
        int count = homeMapper.getProductCount();
        return Result.ok(count);
    }

    public Result getNMProductByPage(PageConfigItem pageConfigItem){
        int pagesize = pageConfigItem.getPageSize();
        int offset = (pageConfigItem.getCurrentPage() - 1) * pageConfigItem.getPageSize();
        List<Product> product = homeMapper.getNMProductByPage(pagesize,offset);
        return Result.ok(product);
    }
}
