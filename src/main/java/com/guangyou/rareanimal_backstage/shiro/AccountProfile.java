package com.guangyou.rareanimal_backstage.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description 自定义authentication对象
 * @author xukai
 * @create 2022-11-05 13:03
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员信息")
public class AccountProfile implements Serializable {

    @ApiModelProperty(name = "管理员ID")
    private Integer adminId;

    @ApiModelProperty(name = "管理员名称")
    private String adminerName;

    @ApiModelProperty(name = "管理员账号")
    private String adminerAccount;

    @ApiModelProperty(name = "管理员头像")
    private String adminerAvatar;

}
