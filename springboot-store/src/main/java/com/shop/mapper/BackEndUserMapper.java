package com.shop.mapper;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackEndUserMapper {
    List<User> backendUser(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    int userEdit(@Param("user")User user);
    int userDel(@Param("uid") Integer uid);
    int userCount();
}
