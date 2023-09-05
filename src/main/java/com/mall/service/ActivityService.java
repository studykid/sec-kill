package com.mall.service;


import com.mall.model.Activity;

/**
 * @author wy
 */
public interface ActivityService {
    /**
     * 生成秒杀活动
     * @param activity
     */
    void genActivity(Activity activity);

    /**
     * 查询缓存中的库存
     * @param key
     * @return
     */
    String getCacheStock(String key);

}
