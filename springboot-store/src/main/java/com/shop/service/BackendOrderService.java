package com.shop.service;

import com.shop.utils.Result;

public interface BackendOrderService {
    Result getBEOrder(Integer currentPage, Integer pageSize);
}
