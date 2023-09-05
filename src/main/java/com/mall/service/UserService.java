package com.mall.service;

import com.mall.common.Resp;
import com.mall.model.User;

import java.math.BigDecimal;

/**
 * @author wy
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
     void register(User user);

    /**
     * 用户登陆
     * @param user
     * @return
     */
     Long login(User user);

    /**
     * 用户充值
     * @param userId
     * @param money
     */
     void recharge(Long userId, double money);

    /**
     * 用户提现
     * @param userId
     * @param money
     */
     void withdraw(Long userId, double money);
}
