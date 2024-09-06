package com.shop.controller;

import com.shop.pojo.PageConfigItem;
import com.shop.service.HomeService;
import com.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/home")
@CrossOrigin
public class HomeController {


    private final HomeService homeService;

    HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @GetMapping("/nmProductByPage/detail")
    public Result loadProductDetail(Integer pid){
        Result result = homeService.loadProductDetail(pid);
        return result;
    }
    @GetMapping("/productType")
    public Result getProductType(){
        Result result = homeService.getProductType();
        return result;
    }
    @GetMapping("/filter")
    public Result getProductByFilter(@RequestParam(name="type", required=false) List<String> type, @RequestParam("priceRange") Integer priceRange,@RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize")Integer pageSize){
        if(type.isEmpty()){
            return homeService.getNMProductByPage(currentPage,pageSize,priceRange);
        }
        Result result = homeService.getProductByFilter(type,priceRange,currentPage,pageSize);
        return result;
    }
    @GetMapping("/filterCount")
    public Result getProductByFilterCount(@RequestParam(name="type", required=false) List<String> type, @RequestParam("priceRange") Integer priceRange, @RequestParam("pageSize")Integer pageSize){
        if(type.isEmpty()){
            return homeService.getProductCount(pageSize,priceRange);
        }
        Result result = homeService.getProductByFilterCount(type,priceRange,pageSize);
        return result;
    }
}
