package com.guangyou.rareanimal_backstage.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-04-12 20:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    public static final String WAIT_AUDIT = "待审核";
    public static final String PASS_AUDIT = "审核通过";
    public static final String NO_PASS_AUDIT = "审核不通过";

    @TableId(value = "activity_id",type = IdType.AUTO)
    private Long activityId;

    private Long publishUid;

    private String activityTitle;

    private String activityDescribe;

    private String activityClassify;

    private String activityPlace;

    private String coverUrl;

    private Long requestTime;

    private Long startTime;

    private Long endTime;

    private Long updateTime;

    //审核状态：待审核、审核通过、审核不通过
    private String auditState;

    //审核所要给的原因
    private String auditReason;

    private Long auditTime;

    private Integer peopleCeiling;

}
