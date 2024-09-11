package com.shop.pojo;

import lombok.Data;

@Data
public class Product {
    private Integer pid;
    private String name;
    private Double price;
    private String image;
    private Integer type;
    private Integer count;
    private Integer quantity;
}
