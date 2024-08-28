package com.shop.controller;

import com.shop.service.BackendService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/backend")
@ResponseBody
@CrossOrigin
public class BackendController {

    private BackendService backendService;

    BackendController(BackendService backendService){
        this.backendService = backendService;
    }
    @GetMapping("/login")
    public Result login(@RequestParam("username") String username,@RequestParam("password") String password){
        Result result = backendService.login(username,password);
        return result;
    }
}
