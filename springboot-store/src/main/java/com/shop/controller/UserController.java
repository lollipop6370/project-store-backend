package com.shop.controller;

import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register/username")
    public Result checkUsername(String username){
        Result result = userService.checkUsername(username);
        return result;
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        Result result = userService.register(user);
        return result;
    }

    @PostMapping("/login")
    public Result userLogin(@RequestBody User user){
        Result result = userService.userLogin(user);
        return result;
    }

}
