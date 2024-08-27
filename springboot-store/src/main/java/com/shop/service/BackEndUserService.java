package com.shop.service;

import com.shop.pojo.User;
import com.shop.utils.Result;

public interface BackEndUserService {
    Result backendUser(Integer currentPage, Integer pageSize);
    Result userEdit(User user);
    Result userDel(Integer uid);
    Result userPageCount(Integer pageSize);
}
