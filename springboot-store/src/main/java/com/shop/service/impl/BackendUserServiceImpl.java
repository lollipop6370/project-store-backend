package com.shop.service.impl;

import com.shop.mapper.BackendUserMapper;
import com.shop.pojo.User;
import com.shop.service.BackendUserService;
import com.shop.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.ceil;

@Service
public class BackendUserServiceImpl implements BackendUserService {

    private BackendUserMapper backEndUserMapper;

    BackendUserServiceImpl(BackendUserMapper backEndUserMapper){
        this.backEndUserMapper = backEndUserMapper;
    }

    public Result backendUser(Integer currentPage, Integer pageSize){
        Integer offset = (currentPage - 1) * pageSize;
        List<User> data = backEndUserMapper.backendUser(offset, pageSize);
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
    public Result userPageCount(Integer pageSize){
        Integer userCount = backEndUserMapper.userCount();
        return Result.ok(ceil(Double.valueOf(userCount)/pageSize));
    }
}
