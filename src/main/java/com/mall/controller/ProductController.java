package com.mall.controller;

import com.mall.common.CodeMsg;
import com.mall.common.ProductStatusEnum;
import com.mall.common.Resp;
import com.mall.service.ProductService;
import com.mall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wy
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 发布商品
     * @param product
     * @return
     */
    @PostMapping("/launchProduct")
    public Resp launchProduct(Product product){
        productService.launchProduct(product);
        return Resp.ok(CodeMsg.PUBLISH_SUCCESS);
    }

    /**
     * 更新库存
     * @param product
     * @return
     */
    @PostMapping("/updateStock")
    public Resp updateStock(Product product){
        productService.updateStock(product);
        return Resp.ok(CodeMsg.UPDATE_STOCK_SUCCESS);
    }

    /**
     * 上架商品
     * @param product
     * @return
     */
    @PostMapping("/listProduct")
    public Resp listProduct(Product product){
        productService.updateStatus(product.getId(), ProductStatusEnum.UP.getValue());
        return Resp.ok(CodeMsg.PUT_ON_SALE_SUCCESS);
    }

    /**
     * 下架商品
     * @param product
     * @return
     */
    @PostMapping("/deListProduct")
    public Resp deListProduct(Product product){
        productService.updateStatus(product.getId(), ProductStatusEnum.DOWN.getValue());
        return Resp.ok(CodeMsg.PULL_OFF_SHELF_SUCCESS);
    }

}
