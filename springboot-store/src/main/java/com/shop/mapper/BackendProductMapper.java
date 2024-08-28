package com.shop.mapper;

import com.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackendProductMapper {
    List<Product> backendProduct(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
    Integer backendProductCount();
}
