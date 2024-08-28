package com.shop.service;

import com.shop.utils.Result;

public interface BackendService {
    Result login(String username, String password);
}
