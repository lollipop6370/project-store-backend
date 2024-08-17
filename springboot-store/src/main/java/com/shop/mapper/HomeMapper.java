package com.shop.mapper;

import com.shop.pojo.PageConfigItem;
import com.shop.pojo.Product;
import com.shop.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomeMapper {
    int getProductCount();

    List<Product> getNMProductByPage(int pageSize, int offset);

    Product loadProductDetail(Integer itemId);
}
