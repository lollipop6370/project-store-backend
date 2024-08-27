package com.shop.mapper;

import com.shop.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackEndUserMapper {
    String login(String username);
    List<User> backendUser();
    int userEdit(@Param("user")User user);
}
