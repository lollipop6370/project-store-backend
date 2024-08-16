package com.shop.service;

import com.shop.pojo.User;
import com.shop.utils.Result;

public interface UserService {

    Result checkUsername(String username);
    Result register(User user);

    Result userLogin(User user);
}
