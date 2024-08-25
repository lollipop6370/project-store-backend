package com.shop.service.impl;

import com.shop.mapper.BackEndUserMapper;
import com.shop.service.BackEndUserService;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;

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
}
