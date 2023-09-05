package com.mall.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author wy
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private long id;
    private String activityName;
    private Long productId;
    private Integer stockNum;
    private double activityPrice;
}
