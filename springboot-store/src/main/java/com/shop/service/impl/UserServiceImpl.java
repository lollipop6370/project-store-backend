package com.shop.service.impl;

import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public Result checkUsername(String username){
        Boolean isExist = userMapper.checkUsername(username);
        return Result.ok(isExist);
    }

    public Result register(User user){
        Boolean isExist = userMapper.checkUsername(user.getUsername());
        if(isExist == true){
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        userMapper.register(user);
        return Result.ok(null);
    }

    public Result userLogin(User user){
        

    }
}
