package com.mall.controller;

import com.mall.common.CodeMsg;
import com.mall.common.Resp;
import com.mall.common.exception.InvalidInputException;
import com.mall.common.exception.LoginInputException;
import com.mall.model.RespUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mall.service.UserService;
import com.mall.model.User;



/**
 * @author wy
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Resp register(User user){
        try{
            userService.register(user);
        }catch (InvalidInputException e){
            return Resp.ok(CodeMsg.REGISTER_FAIL);
        }
        return Resp.ok(CodeMsg.REGISTER_SUCCESS);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Resp login(User user){
        RespUserDto respUserDto = new RespUserDto();
        try{
            Long id = userService.login(user);
            respUserDto.setUserId(id);
        }catch (InvalidInputException e){
            return Resp.ok(CodeMsg.REGISTER_FAIL);
        }catch (LoginInputException e){
            return Resp.ok(CodeMsg.REGISTER_FAIL_01);
        }
        return Resp.ok(respUserDto);
    }

    /**
     * 充值
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/recharge")
    public Resp recharge(Long userId, Double money){
        userService.recharge(userId,money);
        return Resp.ok(CodeMsg.RECHARGE_SUCCESS);
    }

    /**
     * 提现
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/withdraw")
    public Resp withdraw(Long userId, Double money){
        userService.withdraw(userId,money);
        return Resp.ok(CodeMsg.WITHDRAW_SUCCESS);
    }




}
