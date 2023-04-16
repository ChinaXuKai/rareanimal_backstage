package com.guangyou.rareanimal_backstage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-03-29 9:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员信息")
public class AdminerVo {

    @ApiModelProperty(value = "管理员id")
    private Long adminerId;

    @ApiModelProperty(value = "管理员名称")
    private String adminerName;

    @ApiModelProperty(value = "管理员账号")
    private String adminerAccount;

    @ApiModelProperty(value = "管理员头像")
    private String adminerAvatar;
}
