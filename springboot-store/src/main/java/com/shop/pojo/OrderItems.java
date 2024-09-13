package com.shop.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItems implements Serializable {
    private Integer oid;
    private Integer pid;
    private Integer quantity;
    private String name;
    private Integer price;
}
