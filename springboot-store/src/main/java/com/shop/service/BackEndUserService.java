package com.shop.service;

import com.shop.pojo.User;
import com.shop.utils.Result;

public interface BackEndUserService {
    Result login(String username, String password);
    Result backendUser();
    Result userEdit(User user);
    Result userDel(Integer uid);
}
