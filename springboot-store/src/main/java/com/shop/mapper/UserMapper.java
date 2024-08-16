package com.shop.mapper;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Boolean checkUsername(String username);
    int register(@Param("user") User user);
    User selectUserByUsername(@Param("username") String username);
}
