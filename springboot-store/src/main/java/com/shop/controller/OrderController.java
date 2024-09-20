package com.shop.controller;

import com.shop.pojo.Order;
import com.shop.pojo.OrderItems;
import com.shop.service.OrderService;
import com.shop.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;
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
    @PostMapping("/createPayment")
    public Result createPayment(@RequestBody Order order){
        MultiValueMap payPara = orderService.createPayment(order);
        return Result.ok(payPara);
    }
    @GetMapping("")
    public Result readOrder(@RequestParam("currentPage")Integer currentPage, @RequestParam("pageSize") Integer pageSize, @RequestHeader String token){
        Result result = orderService.readOrder(currentPage, pageSize, token);
        return result;
    }
    @GetMapping("/items")
    public Result getOrderDetail(@RequestParam("oid")Integer oid){
        Result result = orderService.getOrderDetail(oid);
        return result;
    }
    @GetMapping("/totalPage")
    public Result getOrderPageCount(@RequestParam("pageSize")Integer pageSize, @RequestHeader String token){
        Result result = orderService.getOrderPageCount(pageSize,token);
        return result;
    }
    @DeleteMapping("/noPayOrder")
    public Result delNoPayOrder(@RequestParam("oid") Integer oid){
        Result result = orderService.delNoPayOrder(oid);
        return result;
    }
    @PostMapping("/payFeedBack")
    public Result payFeedBack(HttpServletRequest request, HttpServletResponse response){
        // 從第三方 API 接收返回的支付結果
        String paymentStatus = request.getParameter("Status");
        String tradeInfo = request.getParameter("TradeInfo");
        String redirectUrl = orderService.payFeedBack(tradeInfo);
        /*try {
            response.sendRedirect(redirectUrl);
        }catch (IOException e){
            e.printStackTrace();
        }*/
        return null;
    }
}
