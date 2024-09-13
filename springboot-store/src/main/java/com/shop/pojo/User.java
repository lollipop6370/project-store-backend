package com.shop.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String email;
}
