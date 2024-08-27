package com.shop.controller;

import com.shop.pojo.User;
import com.shop.service.BackEndUserService;
import com.shop.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/backend/user")
@ResponseBody
@CrossOrigin
public class BackEndUserController {

    private BackEndUserService backEndUserService;

    BackEndUserController(BackEndUserService backEndUserService){
        this.backEndUserService = backEndUserService;
    }
    @GetMapping("")
    public Result backendUser(@RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize") Integer pageSize){
        Result result = backEndUserService.backendUser(currentPage, pageSize);
        return result;
    }
    @PostMapping("")
    public Result userEdit(@RequestBody User user){
        Result result = backEndUserService.userEdit(user);
        return result;
    }
    @DeleteMapping("")
    public Result userDel(@RequestParam("uid") Integer uid){
        Result result = backEndUserService.userDel(uid);
        return result;
    }
    @GetMapping("/count")
    public Result userPageCount(@RequestParam("pageSize") Integer pageSize){
        Result result = backEndUserService.userPageCount(pageSize);
        return result;
    }
}
