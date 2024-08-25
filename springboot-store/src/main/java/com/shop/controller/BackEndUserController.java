package com.shop.controller;

import com.shop.service.BackEndUserService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/backend")
@ResponseBody
@CrossOrigin
public class BackEndUserController {

    private BackEndUserService backEndUserService;

    BackEndUserController(BackEndUserService backEndUserService){
        this.backEndUserService = backEndUserService;
    }
    @GetMapping("/login")
    public Result backendLogin(String username, String password){
        System.out.println(username + password);
        Result result = backEndUserService.login(username,password);
        return result;
    }
}
