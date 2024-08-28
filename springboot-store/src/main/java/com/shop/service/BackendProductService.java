package com.shop.service;

import com.shop.utils.Result;

public interface BackendProductService {
    Result backendProduct(Integer currentPage, Integer pageSize);
    Result backendProductPage(Integer pageSize);
}
