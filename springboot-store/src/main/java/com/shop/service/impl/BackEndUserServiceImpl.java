package com.shop.service.impl;

import com.shop.mapper.BackEndUserMapper;
import com.shop.pojo.User;
import com.shop.service.BackEndUserService;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackEndUserServiceImpl implements BackEndUserService {

    private BackEndUserMapper backEndUserMapper;

    BackEndUserServiceImpl(BackEndUserMapper backEndUserMapper){
        this.backEndUserMapper = backEndUserMapper;
    }

    public Result login(String username, String password){
        String pass = backEndUserMapper.login(username);
        if(!"".equals(password) && password.equals(pass))
            return Result.ok(true);
        else
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }
    public Result backendUser(){
        List<User> data = backEndUserMapper.backendUser();
        return Result.ok(data);
    }
    public Result userEdit(User user){
        backEndUserMapper.userEdit(user);
        return Result.ok(null);
    }
    public Result userDel(Integer uid){
        backEndUserMapper.userDel(uid);
        return Result.ok(null);
    }
}
