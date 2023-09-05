package com.mall.common;

public enum OrderStatusEnum {
    /**
     * 初始化
     */
    INIT(0, "初始化"),

    /**
     * 成功待支付
     */
    SUCCESS_WAIT_PAY(1, "成功待支付"),

    /**
     * 成功已支付
     */
    SUCCESS_PAY(2, "成功已支付"),

    /**
     * 处理中
     */
    PROCESSING(3, "处理中"),

    /**
     * 失败
     */
    FAIL(4, "失败"),

    /**
     * 订单取消
     */
    CANCEL(5, "订单取消");

    private int code;
    private String name;

    OrderStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据代码获取枚举
     *
     * @param code 代码
     * @return 枚举
     */
    public static OrderStatusEnum getByCode(int code) {
        for (OrderStatusEnum orderStatus : values()) {
            if (orderStatus.code == code) {
                return orderStatus;
            }
        }
        return null;
    }
}
