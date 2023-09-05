package com.mall.service;


/**
 * @author wy
 */
public interface OrderService {
    /**
     * 提交秒杀订单，参加秒杀活动
     * @param userId
     * @param productId
     * @param buyNum
     * @return
     */
    String submitOrder(Long userId,Long productId,int buyNum);

    /**
     * 支付订单
     * @param userId
     * @param productId
     * @param orderId
     */
    void payOrder(Long userId, Long productId,String orderId);

}
