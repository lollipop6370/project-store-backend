package com.shop.pojo;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private String image;
    private Integer type;
}
