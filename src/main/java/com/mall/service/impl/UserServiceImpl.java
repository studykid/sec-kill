package com.mall.service.impl;

import com.mall.common.MallString;
import com.mall.common.exception.InvalidInputException;
import com.mall.common.exception.LoginInputException;
import com.mall.mapper.UserMapper;
import com.mall.util.MD5Util;
import com.mall.util.MoneyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.mall.service.UserService;
import com.mall.model.User;
import org.springframework.stereotype.Service;


/**
 * @author wy
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void register(User user) {
        log.info("user|register|userName:{}",user.getUsername());
        if(checkUser(user)){
            throw new InvalidInputException(400,"Invalid parameter");
        }
        String password = MD5Util.formPassToDBPass(user.getPassword(), MallString.md5_salt);
        user.setPassword(password);
        userMapper.saveUser(user);
    }

    /**
     * 校验用户的用户名和密码
     * @param user
     * @return
     */
    private boolean checkUser(User user) {
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public Long login(User user) {
        log.info("user|login|userName:{}",user.getUsername());
        if(checkUser(user)){
            throw new InvalidInputException(400,"Invalid parameter");
        }
        String password = MD5Util.formPassToDBPass(user.getPassword(),MallString.md5_salt);
        user.setPassword(password);
        Long userId = userMapper.find(user);
        if(userId == null){
            throw new LoginInputException(400,"Invalid userName or password");
        }
        return  userId;
    }

    @Override
    public void recharge(Long userId, double money) {
        User user = userMapper.findById(userId);
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setBalance(user.getBalance() + MoneyUtil.doubleToCents(money));
        updateUser.setVersion(user.getVersion() + 1);
        int rows = userMapper.updateUser(updateUser);

        // 如果更新失败，说明余额已被其他线程修改，则抛出异常
        if (rows == 0) {
            throw new RuntimeException("余额已被其他线程修改");
        }
    }

    @Override
    public void withdraw(Long userId, double money) {
        // 获取用户信息
        User user = userMapper.findById(userId);

        // 检查用户余额是否充足
        if (user.getBalance() - MoneyUtil.doubleToCents(money) < 0) {
            throw new RuntimeException("余额不足");
        }

        // 使用乐观锁更新余额
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setBalance(user.getBalance() - MoneyUtil.doubleToCents(money));
        updateUser.setVersion(user.getVersion() + 1);
        int rows = userMapper.updateUser(updateUser);

        // 如果更新失败，说明余额已被其他线程修改，则抛出异常
        if (rows == 0) {
            throw new RuntimeException("余额已被其他线程修改");
        }
    }

}
