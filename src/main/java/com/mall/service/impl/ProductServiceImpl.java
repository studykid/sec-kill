package com.mall.service.impl;


import com.mall.common.ProductStatusEnum;
import com.mall.mapper.ProductMapper;
import com.mall.service.ProductService;
import com.mall.model.Product;
import com.mall.util.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author wy
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public void launchProduct(Product product) {
        product.setStatus(ProductStatusEnum.INIT.getValue());
        product.setPrice(MoneyUtil.doubleToCents(product.getPrice()));
        productMapper.saveProduct(product);
    }

    @Override
    public void updateStock(Product product) {
        productMapper.updateStock(product);
    }

    @Override
    public void updateStatus(Long id, int status) {
        productMapper.updateStatus(id,status);
    }
}
