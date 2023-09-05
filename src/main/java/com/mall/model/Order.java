package com.mall.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private long id;
    private String orderId;
    private Long productId;
    private Long userId;
    private Integer buyNum;
    private double amount;
    private Date orderTime;
    private Integer status; //订单状态  0：初始化，1：成功待支付，2：完成，3：处理中，4：失败，5：订单取消

}
