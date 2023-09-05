package com.mall.mapper;

import com.mall.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author wy
 */
@Mapper
public interface ProductMapper {
    /**
     * 发布商品
     * @param product
     */
    @Insert("INSERT into mall_product_info (name,price,description,stock,status) VALUES (#{name},#{price},#{description},#{stock},#{status});")
    void saveProduct(Product product);

    /**
     * 更新库存
     * @param product
     */
    @Update("update mall_product_info  set stock = #{stock} where id = #{id};")
    void updateStock(Product product);

    /**
     * 查询产品库存
     * @param productId
     * @return
     */
    @Select("select stock from mall_product_info where id =  #{productId};")
    int getStock(Long productId);

    /**
     * 更新状态
     * @param id
     * @param status
     */
    @Update("update mall_product_info  set status = #{status} where id =  #{id};")
    void updateStatus(Long id,int status);

    /**
     * 根据主键查询商品
     * @param ProductId
     * @return
     */
    @Select("SELECT name,description,price,stock,status  from  mall_product_info where id = #{id};")
    Product findById(Long ProductId);
}
