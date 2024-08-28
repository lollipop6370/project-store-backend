package com.shop.service.impl;

import com.shop.mapper.BackendMapper;
import com.shop.service.BackendService;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;

@Service
public class BackendServiceImpl implements BackendService {
    private BackendMapper backEndMapper;

    BackendServiceImpl(BackendMapper backEndMapper){
        this.backEndMapper = backEndMapper;
    }
    public Result login(String username,String password){
        String pass = backEndMapper.login(username);
        if(!"".equals(password) && password.equals(pass))
            return Result.ok(true);
        else
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }
}
