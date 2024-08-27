package com.shop.mapper;

import org.apache.ibatis.annotations.Param;

public interface BackEndMapper {
    String login(@Param("username") String username);
}
