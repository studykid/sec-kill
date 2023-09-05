package com.mall.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户
 * @author wy
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private double balance;
    private int version;
}
