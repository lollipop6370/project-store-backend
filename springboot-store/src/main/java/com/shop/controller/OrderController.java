package com.shop.controller;

import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.service.OrderService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    private OrderService orderService;
    OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping("")
    public Result newOrder(@RequestBody Order order){
        Result result = orderService.newOrder(order);
        return result;
    }
    @PostMapping("/items")
    public Result newOrderItems(@RequestBody List<OrderItems> orderItems){
        Result result = orderService.newOrderItems(orderItems);
        return result;
    }
}
