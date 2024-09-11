package com.shop.mapper;

import com.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<Product> getUserCartByUId(@Param("uid") Integer uid);
    int updateList(@Param("uid") Long uid,@Param("pid") Integer pid,@Param("quantity") Integer quantity);
    int deleteItem(@Param("uid") Long uid,@Param("pid") Integer pid);
    int clearItem(@Param("uid") Long uid);
    int newItem(@Param("uid") Long uid,@Param("pid") Integer pid,@Param("quantity") Integer quantity);
    int productCountEdit(@Param("itemId")Integer itemId, @Param("count")Integer count);
}
