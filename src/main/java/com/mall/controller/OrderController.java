package com.mall.controller;

import com.mall.common.CodeMsg;
import com.mall.common.Resp;
import com.mall.model.RespOrderDto;
import com.mall.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wy
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 生成秒杀订单
     * @return
     */
    @PostMapping("/submitOrder")
    public Resp<RespOrderDto> submitOrder(Long userId, Long productId, int buyNum){
            String orderId = orderService.submitOrder(userId,productId,buyNum);
            if(StringUtils.isEmpty(orderId)){
                return Resp.error(CodeMsg.SECKILL_FAIL);
            }
            RespOrderDto dto = RespOrderDto.builder().orderId(orderId).build();
            return Resp.ok(dto);
    }

    /**
     * 支付订单
     * @return
     */
    @PostMapping("/payOrder")
    public Resp payOrder(Long userId, Long productId,String orderId){
        orderService.payOrder(userId,productId,orderId);
        return Resp.ok(CodeMsg.ORDER_PAY_SUCCESS);
    }

}
