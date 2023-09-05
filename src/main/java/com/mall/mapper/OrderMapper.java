package com.mall.mapper;

import com.mall.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author wy
 */
public interface OrderMapper {

    /**
     * 创建订单
     * @param order
     * @return
     */
    @Insert("INSERT into mall_order_info (orderId,productId,userId,buyNum,amount,orderTime,status) " +
            "VALUES (#{orderId},#{productId},#{userId},#{buyNum},#{amount},#{orderTime},#{status});")
    void insert(Order order);

    /**
     * 更新状态
     * @param
     * @return
     */
    @Update("update mall_order_info  set status = #{status}  where orderId = #{orderId};")
    int updateOrderStatus(String orderId ,int status);

    /**
     * 查询订单
     * @param orderId
     * @return
     */
    @Select("SELECT orderId,productId,userId,buyNum,amount from mall_order_info where orderId = #{orderId};")
    Order findById(String orderId);

    /**
     * 根据订单号和状态查询订单
     * @param orderId
     * @param status
     * @return
     */
    @Select("SELECT productId from mall_order_info where orderId = #{orderId} and status = #{status}")
    String selectOrder(String orderId,int status);


}
