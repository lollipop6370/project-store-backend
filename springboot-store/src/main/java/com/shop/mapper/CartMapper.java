package com.shop.mapper;

import com.shop.pojo.Product;

import java.util.List;

public interface CartMapper {
    List<Product> getUserCartByUId(Integer uid);

    int updateList(Long uid, Integer pid, Integer quantity);

    int deleteItem(Long uid, Integer pid);

    int newItem(Long uid, Integer pid, Integer quantity);
}
