package com.shop.service.impl;

import com.shop.mapper.UserMapper;
import com.shop.pojo.User;
import com.shop.service.UserService;
import com.shop.utils.JwtProvider;
import com.shop.utils.Result;
import com.shop.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private JwtProvider jwtProvider;

    UserServiceImpl(UserMapper userMapper, JwtProvider jwtProvider){
        this.userMapper = userMapper;
        this.jwtProvider = jwtProvider;
    }

    /**
     * 確認username是否占用
     * @param username:String
     * @return isExidt:Boolean
     */
    public Result checkUsername(String username){
        Boolean isExist = userMapper.checkUsername(username);
        return Result.ok(isExist);
    }

    /**
     * 用戶註冊
     * @param user:User
     * @return null
     */
    public Result register(User user){
        Boolean isExist = userMapper.checkUsername(user.getUsername());
        if(isExist == true){
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        userMapper.register(user);
        return Result.ok(null);
    }

    /**
     * 用戶登入
     * @param user:User
     * @return token:String
     */
    public Result userLogin(User user){
        User loginUser1 = userMapper.selectUserByUsername(user.getUsername());
        if(loginUser1 == null){ //數據庫找不到此帳號
            System.out.println("帳號錯誤");
            return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        //判斷密碼
        if("".equals(loginUser1.getPassword()) || !user.getPassword().equals(loginUser1.getPassword())){
            System.out.println("密碼錯誤");
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }

        //返回token
        String token = jwtProvider.createToken(Long.valueOf(loginUser1.getUid()));

        Map data = new HashMap();
        data.put("token",token);
        data.put("uid",jwtProvider.getUserId(token));
        data.put("username",user.getUsername());
        System.out.println("登入成功: " + token);
        return Result.ok(data);
    }
    /**
     * 用tooken查UID
     */
    public Result findUId(String token){
        Long uid = jwtProvider.getUserId(token);
        return Result.ok(uid);
    }

    public Result checkLogin(String token){
        Boolean isExpiration = jwtProvider.isExpiration(token);
        return Result.ok(!isExpiration);
    }
}
