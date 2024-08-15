package com.shop.service;

import com.shop.pojo.PageConfigItem;
import com.shop.utils.Result;

public interface HomeService {
    Result getProductCount();

    Result getNMProductByPage(PageConfigItem pageConfigItem);
}
