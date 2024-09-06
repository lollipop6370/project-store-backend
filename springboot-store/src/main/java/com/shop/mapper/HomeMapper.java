package com.shop.mapper;

import com.shop.pojo.PageConfigItem;
import com.shop.pojo.Product;
import com.shop.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomeMapper {
    int getProductCount(@Param("priceRange") Integer priceRange);
    List<Product> getNMProductByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("priceRange")Integer priceRange);
    Product loadProductDetail(@Param("pid") Integer pid);
    String[] getProductType();
    List<Product> getProductByFilter(@Param("type") List<String> type,@Param("priceRange") Integer priceRange,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    Integer getProductByFilterCount(@Param("type") List<String> type,@Param("priceRange") Integer priceRange);
}
