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
     * 獲取商品總頁數
     * @return int
     */
    public Result getProductCount(Integer pageSize, Integer priceRange){
        int count = homeMapper.getProductCount(priceRange);
        return Result.ok(Math.ceil(Double.valueOf(count)/pageSize));
    }

    /**
     * 依照分頁大小來回傳商品
     * @param
     * @return product
     */
    public Result getNMProductByPage(Integer currentPage, Integer pageSize, Integer priceRange){
        Integer offset = (currentPage - 1) * pageSize;
        List<Product> product = homeMapper.getNMProductByPage(offset,pageSize,priceRange);
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

    public Result getProductType(){
        String[] data = homeMapper.getProductType();
        return Result.ok(data);
    }
    public Result getProductByFilter(List<String> type, Integer priceRange, Integer currentPage, Integer pageSize){
        Integer offset = (currentPage - 1) * pageSize;
        List<Product>data = homeMapper.getProductByFilter(type,priceRange,offset,pageSize);
        return Result.ok(data);
    }
    public Result getProductByFilterCount(List<String> type, Integer priceRange, Integer pageSize){
        Integer count = homeMapper.getProductByFilterCount(type,priceRange);
        return Result.ok(Math.ceil(Double.valueOf(count)/pageSize));
    }
}
