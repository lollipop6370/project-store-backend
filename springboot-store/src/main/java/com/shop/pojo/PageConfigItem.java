package com.shop.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageConfigItem implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
}
