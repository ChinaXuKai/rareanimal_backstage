package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-04-12 21:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCover {

    private Long id;

    private String coverUrl;

    private Long activityId;
}
