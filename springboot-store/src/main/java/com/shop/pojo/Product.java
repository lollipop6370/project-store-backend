package com.shop.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private Integer pid;
    private String name;
    private Double price;
    private String image;
    private Integer type;
    private Integer count;
    private Integer quantity;
}
