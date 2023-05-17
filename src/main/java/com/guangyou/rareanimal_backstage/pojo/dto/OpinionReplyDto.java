package com.guangyou.rareanimal_backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-05-15 23:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "回复用户意见所需要的参数")
public class OpinionReplyDto {

    @ApiModelProperty(value = "该回复所对应的意见id")
    private Long opinionId;

    @ApiModelProperty(value = "回复的内容")
    private String replyContent;

}
