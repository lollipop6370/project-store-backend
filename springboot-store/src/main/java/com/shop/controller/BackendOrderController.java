package com.shop.controller;

import com.shop.service.BackendOrderService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/backend/order")
@CrossOrigin
public class BackendOrderController {
    private BackendOrderService backendOrderService;
    BackendOrderController(BackendOrderService backendOrderService){
        this.backendOrderService = backendOrderService;
    }
    @GetMapping("")
    public Result getBEOrder(@RequestParam("currentPage")Integer currentPage, @RequestParam("pageSize")Integer pageSize){
        Result result = backendOrderService.getBEOrder(currentPage, pageSize);
        return result;
    }
}
