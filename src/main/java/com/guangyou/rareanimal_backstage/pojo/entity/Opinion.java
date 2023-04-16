package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-04-12 22:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opinion {

    private Long opinionId;

    private Long userId;

    private String opinionContent;

    private Long submitTime;

    private Long updateTime;

    private Integer isDelete;

}
