package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-04-12 21:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCustomTag {

    private Long id;

    private String tagDescribe;

    private Long activityId;

}
