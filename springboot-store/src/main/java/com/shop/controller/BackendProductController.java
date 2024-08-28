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
    @GetMapping("")
    public Result backendProduct(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Result result = backendProductService.backendProduct(currentPage,pageSize);
        return result;
    }
}
