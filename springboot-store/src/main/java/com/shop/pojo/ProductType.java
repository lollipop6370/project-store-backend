package com.shop.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductType implements Serializable {
    private Integer tid;
    private String tName;
}
