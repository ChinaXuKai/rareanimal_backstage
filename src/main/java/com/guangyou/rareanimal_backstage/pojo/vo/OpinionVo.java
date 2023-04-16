package com.guangyou.rareanimal_backstage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-04-12 22:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户意见展示")
public class OpinionVo {

    @ApiModelProperty(value = "意见主键id")
    private Long opinionId;

    @ApiModelProperty(value = "意见内容")
    private String opinionContent;


    @ApiModelProperty(value = "发表该意见的用户的信息")
    private UserVo userVo;

    @ApiModelProperty(value = "发表的时间")
    private String submitTime;

    @ApiModelProperty(value = "修改的时间")
    private String updateTime;

}
