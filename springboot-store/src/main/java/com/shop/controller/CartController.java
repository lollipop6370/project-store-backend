package com.shop.controller;

import com.shop.service.CartService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    private CartService cartService;
    CartController(CartService cartService){
        this.cartService = cartService;
    }
    @GetMapping("")
    public Result getUserCart(Integer uid){
        Result result = cartService.getUserCart(uid);
        return result;
    }

    @GetMapping("/updateList")
    public Result updateList(@RequestHeader String token, Integer id, Integer quantity){
        Result result = cartService.updateList(token,id,quantity);
        return result;
    }

    @GetMapping("/deleteItem")
    public Result deleteItem(@RequestHeader String token, Integer id){
        Result result = cartService.deleteItem(token,id);
        return result;
    }

    @GetMapping("/newItem")
    public Result newItem(@RequestHeader String token, Integer id, Integer count){
        System.out.println(id +" " + count);
        Result result = cartService.newItem(token,id,count);
        return result;
    }
}
