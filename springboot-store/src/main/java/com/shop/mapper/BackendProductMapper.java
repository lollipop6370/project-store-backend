package com.shop.mapper;

import com.shop.pojo.Product;
import com.shop.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackendProductMapper {
    List<Product> backendProduct(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
    Integer backendProductCount();
    int backendProductEdit(@Param("product") Product product);
    int backendProductDel(@Param("pid") Integer pid);
    int backendProductNew(@Param("product")Product product);
    List<ProductType> getTypeName();
}
