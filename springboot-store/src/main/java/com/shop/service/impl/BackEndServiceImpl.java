package com.shop.service.impl;

import com.shop.mapper.BackEndMapper;
import com.shop.service.BackEndService;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;

@Service
public class BackEndServiceImpl implements BackEndService {
    private BackEndMapper backEndMapper;

    BackEndServiceImpl(BackEndMapper backEndMapper){
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
