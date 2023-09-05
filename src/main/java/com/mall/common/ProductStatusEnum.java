package com.mall.common;

/**
 * @author wy
 */

public enum ProductStatusEnum {
    INIT(0,"初始化"),
    DOWN(1, "下架"),
    UP(2, "上架");

    private int value;
    private String name;

    ProductStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}