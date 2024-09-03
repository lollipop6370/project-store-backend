package com.shop.pojo;

import lombok.Data;

@Data
public class OrderItems {
    private Integer oid;
    private Integer pid;
    private Integer quantity;
    private String name;
    private Integer price;
}
