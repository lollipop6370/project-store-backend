package com.shop.service;

import com.shop.utils.Result;

public interface CartService {
    Result getUserCart(Integer uid);

    Result updateList(String token, Integer id, Integer quantity);
}
