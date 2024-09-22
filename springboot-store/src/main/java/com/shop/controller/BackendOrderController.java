package com.shop.controller;

import com.shop.pojo.Order;
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
    @GetMapping("/items")
    public Result getBEOrderItems(@RequestParam("oid") Integer oid){
        Result result = backendOrderService.getBEOrderItems(oid);
        return result;
    }
    @PutMapping("")
    public Result editBEOrderStatus(@RequestBody Order order){
        Result result = backendOrderService.editBEOrderStatus(order);
        return result;
    }
    @GetMapping("/totalPage")
    public Result getBEOrderCount(@RequestParam("pageSize")Integer pageSize){
        Result result = backendOrderService.getBEOrderCount(pageSize);
        return result;
    }
    @DeleteMapping("")
    public Result backendDelOrder(@RequestParam("oid") Integer oid){
        Result result = backendOrderService.backendDelOrder(oid);
        return result;
    }
}
