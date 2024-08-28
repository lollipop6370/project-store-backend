package com.shop.controller;

import com.shop.service.BackendProductService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/backend/product")
@ResponseBody
@CrossOrigin
public class BackendProductController {
    private BackendProductService backendProductService;
    BackendProductController(BackendProductService backendProductService){
        this.backendProductService = backendProductService;
    }

    /**
     * 獲取商品資訊
     * @param currentPage :Integer
     * @param pageSize :Integer
     * @return Product
     */
    @GetMapping("")
    public Result backendProduct(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Result result = backendProductService.backendProduct(currentPage,pageSize);
        return result;
    }

    /**
     * 獲取商品總頁數
     * @param pageSize:Integer
     * @return Integer
     */
    @GetMapping("/page")
    public Result backendProductPage(@RequestParam("pageSize") Integer pageSize){
        Result result = backendProductService.backendProductPage(pageSize);
        return result;
    }
}
