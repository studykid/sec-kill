package com.mall.model;

import lombok.Data;


/**
 * 商品
 * @author wy
 */
@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer stock;
    /**
     * 0- 初始化,1 - 上架,2 - 下架
     */
    private Integer status;
    private double price;
}
