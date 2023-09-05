package com.mall.service;

import com.mall.model.Product;

/**
 * @author wy
 */
public interface ProductService {
    /**
     * 发布商品
     * @param product
     */
    void launchProduct(Product product);

    /**
     * 更新库存
     * @param product
     */
    void updateStock(Product product);


    /**
     * 更新状态
     * @param id
     * @param status
     */
    void updateStatus(Long id,int status);
}
