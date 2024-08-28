package com.shop.service;

import com.shop.pojo.Product;
import com.shop.utils.Result;

public interface BackendProductService {
    Result backendProduct(Integer currentPage, Integer pageSize);
    Result backendProductPage(Integer pageSize);
    Result backendProductEdit(Product product);
    Result backendProductDel(Integer id);
}
