package com.shop.mapper;

import org.apache.ibatis.annotations.Param;

public interface BackendMapper {
    String login(@Param("username") String username);
}
