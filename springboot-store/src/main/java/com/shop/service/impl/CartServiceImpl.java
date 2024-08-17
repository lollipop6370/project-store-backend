package com.shop.service.impl;

import com.shop.mapper.CartMapper;
import com.shop.pojo.Product;
import com.shop.service.CartService;
import com.shop.utils.JwtProvider;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartMapper cartMapper;
    private JwtProvider jwtProvider;

    CartServiceImpl(CartMapper cartMapper, JwtProvider jwtProvider){
        this.cartMapper = cartMapper;
        this.jwtProvider = jwtProvider;
    }
    /**
     * 獲取用戶的購物車
     * @param uid Integer
     * @return Product
     */
    public Result getUserCart(Integer uid){
        List<Product> list = cartMapper.getUserCartByUId(uid);
        return Result.ok(list);
    }

    /**
     * 修改購物車現有商品數量
     * @param token :String
     * @param id :Integer
     * @param quantity :Integer
     * @return null
     */
    public Result updateList(String token, Integer id, Integer quantity){
        Long uid = jwtProvider.getUserId(token);
        cartMapper.updateList(uid,id,quantity);
        return Result.ok(null);
    }
}
