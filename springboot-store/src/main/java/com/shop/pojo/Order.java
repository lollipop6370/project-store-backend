package com.shop.pojo;

import lombok.Data;

@Data
public class Order {
    private Integer oid;
    private Integer uid;
    private Integer totalPrice;
    private String address;
    private String city;
    private Integer postal;
    private String receiver;
    private Integer status;
    private String createTime;
}
