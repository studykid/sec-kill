package com.mall.controller;

import com.mall.common.CodeMsg;
import com.mall.common.Resp;
import com.mall.common.exception.OrderLackStockException;
import com.mall.model.Activity;
import com.mall.service.ActivityService;
import com.mall.util.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * @author wy
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    /**
     * 生成秒杀活动
     * @return
     */
    @PostMapping("/genActivity")
    public Resp genFlashSale(String activityName,Long productId,int stockNum,double activityPrice){
        try {
            Activity activity = Activity.builder()
                    .activityName(activityName)
                    .productId(productId)
                    .stockNum(stockNum)
                    .activityPrice(MoneyUtil.doubleToCents(activityPrice))
                    .build();
            activityService.genActivity(activity);
        } catch (OrderLackStockException e) {
            return Resp.error(CodeMsg.STOCK_ERROR);
        }
        return Resp.ok(CodeMsg.GEN_SECKILL_SUCCESS);
    }

    /**
     * 查看redis中还有多少库存
     * @return
     */
    @GetMapping("/getStock")
    public Resp getStock(Long productId){
        return Resp.ok(activityService.getCacheStock("mall_stock"+productId));
    }

}
