package com.shop.service;

import com.shop.utils.Result;

public interface CartService {
    Result getUserCart(Integer uid);

    Result updateList(String token, Integer pid, Integer quantity);
    Result deleteItem(String token, Integer pid);
    Result clearItem(String token);
    Result newItem(String token, Integer pid, Integer quantity);
    Result productCountEdit(Integer itemId, Integer count);
}
