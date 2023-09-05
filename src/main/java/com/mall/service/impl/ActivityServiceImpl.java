package com.mall.service.impl;

import com.mall.common.MallString;
import com.mall.common.exception.OrderLackStockException;
import com.mall.mapper.ActivityMapper;
import com.mall.mapper.ProductMapper;
import com.mall.model.Activity;
import com.mall.service.ActivityService;
import com.mall.util.RedisTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author wy
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

     @Autowired
     ActivityMapper activityMapper;
     @Autowired
     ProductMapper productMapper;

     @Autowired
     RedisTools redisTool;

    /**
     * 生成秒杀活动
     *
     * @param activity
     * @return
     */
    @Override
    public void genActivity(Activity activity) {
        Long productId = activity.getProductId();
        //活动所需库存
        int quantity = activity.getStockNum();
        //查询库存
        int stock = productMapper.getStock(productId);
        if(stock < quantity){
            log.info("FlashSaleService.genFlashSale.库存不足.所需库存{},现有库存",quantity,stock);
            throw new OrderLackStockException(500,"库存不足");
        }
        //将库存缓存到REDIS
        redisTool.set(MallString.redis_prx+String.valueOf(productId), String.valueOf(quantity));
        //生成秒杀活动表
        activityMapper.insert(activity);

    }

    @Override
    public String getCacheStock(String key){
       return redisTool.get(key);
    }



}
