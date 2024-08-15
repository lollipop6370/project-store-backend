package com.shop.controller;

import com.shop.service.HomeService;
import com.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/home")
@CrossOrigin
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/productCount")
    public Result getProductCount(){
        Result result = homeService.getProductCount();
        return result;
    }
}
