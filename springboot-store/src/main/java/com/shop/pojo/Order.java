package com.shop.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    private Integer oid;
    private Integer uid;
    private Integer totalPrice;
    private String address;
    private String city;
    private Integer postal;
    private String receiver;
    private Integer status;
    private String createTime;
    private Integer pay;
}
