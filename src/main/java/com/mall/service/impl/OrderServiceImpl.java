package com.mall.service.impl;

import com.mall.common.MallString;
import com.mall.common.OrderStatusEnum;
import com.mall.mapper.ActivityMapper;
import com.mall.mapper.OrderMapper;
import com.mall.mapper.ProductMapper;
import com.mall.mapper.UserMapper;
import com.mall.model.Activity;
import com.mall.model.Order;
import com.mall.model.Product;
import com.mall.model.User;
import com.mall.service.OrderService;
import com.mall.util.RedisTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wy
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private static Map localHashMap = new HashMap();

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTools redisTool;


    @Override
   // @MyRateLimit
    public String submitOrder(Long userId, Long productId, int buyNum){
            if(localHashMap.containsKey(userId+productId)){
                log.info("重复下单");
                return null;
            }
            //查看库存
            Long count = redisTool.evalsha(MallString.redis_prx +productId+"{111}",buyNum+"{111}");
            if(count == 0 || count < 0 ){
                //throw new OrderLackStockException(500,"mall_order_info");
                log.info("库存不足");
                return null;
            }
            //下单-初始化
            Random random = new Random(10000);
            String orderId = String.valueOf(System.currentTimeMillis())+random.nextInt();
            Order order = new Order();

            Activity activity = activityMapper.selectByProductId(String.valueOf(productId));

            order.setOrderId(orderId);
            order.setAmount(activity.getActivityPrice() * buyNum);
            order.setStatus(OrderStatusEnum.INIT.getCode());
            order.setProductId(productId);
            order.setUserId(userId);
            order.setOrderTime(new Date());
            order.setBuyNum(buyNum);

            orderMapper.insert(order);
            //更新活动库存
            activityMapper.updateStockNum(activity.getId(),buyNum);

            //更新订单-下单成功待支付
            orderMapper.updateOrderStatus(orderId,OrderStatusEnum.SUCCESS_WAIT_PAY.getCode());
            //将
            localHashMap.put(userId+productId,null);
            return orderId;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payOrder(Long userId, Long productId,String orderId) {
             // 检查请求幂等 订单是否是否已被支付
            String record = orderMapper.selectOrder(orderId,OrderStatusEnum.SUCCESS_PAY.getCode());
             if (StringUtils.isNotEmpty(record)) {
                 throw new RuntimeException("Order already pay");
             }

            // 获取订单
            Order order = orderMapper.findById(orderId);
            if (order == null) {
                throw new RuntimeException("Order not found");
            }

            // 获取商品
            Product product = productMapper.findById(order.getProductId());
            if (product == null) {
                throw new RuntimeException("Product not found");
            }

            // 获取用户
            User user = userMapper.findById(order.getUserId());
            if (user == null) {
                throw new RuntimeException("User not found");
            }

            // 检查用户余额是否足够
            if (user.getBalance()  < order.getAmount()) {
                throw new RuntimeException("Insufficient balance");
            }

            //扣除用户余额
            User updateUser = new User();
            updateUser.setBalance(user.getBalance() - order.getAmount());
            updateUser.setId(userId);
            updateUser.setVersion(user.getVersion() + 1);
            int rows = userMapper.updateUser(updateUser);
            if (rows == 0) {
                 throw new RuntimeException("余额已被其他线程修改");
            }
            // 更新订单为已支付
            orderMapper.updateOrderStatus(order.getOrderId(),OrderStatusEnum.SUCCESS_PAY.getCode());
    }
}
