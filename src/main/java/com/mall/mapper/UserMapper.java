package com.mall.mapper;

import com.mall.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @author wy
 */
@Mapper
public interface UserMapper {
    /**
     * 注册
     * @param user
     */
    @Insert("INSERT into mall_user_info (username,password) VALUES (#{username},#{password});")
    void saveUser(User user);


    /**
     * 登陆
     * @param user
     */
    @Select("SELECT id from mall_user_info where username = #{username} and password = #{password};")
    Long find(User user);

    /**
     * 更改用户
     * @param user
     * @return
     */
    @Update("update mall_user_info  set balance = #{balance},gmt_modified = now(),version = #{version} where id = #{id} and version = #{version} -1 ;")
    int updateUser(User user);

    /**
     * 根据id查找用户信息
     * @param userId
     * @return
     */
    @Select("SELECT id,username,password,balance,version  from  mall_user_info where id = #{id};")
    User findById(Long userId);
}
