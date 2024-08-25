package com.shop.service;

import com.shop.utils.Result;

public interface BackEndUserService {
    Result login(String username, String password);
}
