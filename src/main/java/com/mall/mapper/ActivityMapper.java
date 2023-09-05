package com.mall.mapper;

import com.mall.model.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @author wy
 */
public interface ActivityMapper {
    /**
     * 创建活动
     * @param activity
     * @return
     */
    @Insert("INSERT into mall_activity_info (activityName,productId,stockNum,activityPrice) VALUES (#{activityName},#{productId},#{stockNum},#{activityPrice});")
    int insert(Activity activity);

    /**
     * 根据商品id查询活动
     * @param productId
     * @return
     */
    @Select("SELECT id,productId,stockNum,activityPrice  from  mall_activity_info where productId = #{productId};")
    Activity selectByProductId(@Param("productId") String productId);


    /**
     * 更新库存
     * @param
     * @return
     */
    @Update("update mall_activity_info set stockNum = stockNum - #{stockNum} where id = #{id};")
    int updateStockNum(@Param("id") Long id,@Param("stockNum") Integer stockNum);

}
