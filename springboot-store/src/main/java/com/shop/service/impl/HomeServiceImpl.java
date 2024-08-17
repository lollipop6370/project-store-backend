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

    /**
     * 獲取商品總數
     * @return int
     */
    public Result getProductCount(){
        int count = homeMapper.getProductCount();
        return Result.ok(count);
    }

    /**
     * 依照分頁大小來回傳商品
     * @param pageConfigItem
     * @return product
     */
    public Result getNMProductByPage(PageConfigItem pageConfigItem){
        int pagesize = pageConfigItem.getPageSize();
        int offset = (pageConfigItem.getCurrentPage() - 1) * pageConfigItem.getPageSize();
        List<Product> product = homeMapper.getNMProductByPage(pagesize,offset);
        return Result.ok(product);
    }

    /**
     * 載入商品詳細資料
     * @param itemId int
     * @return product
     */
    public Result loadProductDetail(Integer itemId){
        Product product = homeMapper.loadProductDetail(itemId);
        return Result.ok(product);
    }
}
