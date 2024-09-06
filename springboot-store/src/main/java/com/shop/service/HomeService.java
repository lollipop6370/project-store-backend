package com.shop.service;

import com.shop.pojo.PageConfigItem;
import com.shop.utils.Result;

import java.util.List;

public interface HomeService {
    Result getProductCount(Integer pageSize, Integer priceRange);
    Result getNMProductByPage(Integer currentPage, Integer pageSize, Integer priceRange);
    Result loadProductDetail(Integer pid);
    Result getProductType();
    Result getProductByFilter(List<String> type, Integer priceRange, Integer currentPage, Integer pageSize);
    Result getProductByFilterCount(List<String> type, Integer priceRange, Integer pageSize);
}
