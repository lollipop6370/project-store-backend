package com.shop.controller;

import com.shop.pojo.PageConfigItem;
import com.shop.service.HomeService;
import com.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/home")
@CrossOrigin
public class HomeController {


    private final HomeService homeService;

    HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @GetMapping("/productCount")
    public Result getProductCount(){
        Result result = homeService.getProductCount();
        return result;
    }

    @PostMapping("/nmProductByPage")
    public Result getNMProductByPage(@RequestBody PageConfigItem pageConfigItem){
        Result result = homeService.getNMProductByPage(pageConfigItem);
        return result;
    }
}
