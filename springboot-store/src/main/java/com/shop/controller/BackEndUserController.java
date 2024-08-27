package com.shop.controller;

import com.shop.pojo.User;
import com.shop.service.BackEndUserService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        Result result = backEndUserService.login(username,password);
        return result;
    }
    @GetMapping("/user")
    public Result backendUser(){
        Result result = backEndUserService.backendUser();
        return result;
    }
    @PostMapping("/user")
    public Result userEdit(@RequestBody User user){
        Result result = backEndUserService.userEdit(user);
        return result;
    }
    @DeleteMapping("/user")
    public Result userDel(Integer uid){
        Result result = backEndUserService.userDel(uid);
        return result;
    }
}
