package com.guangyou.rareanimal_backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-05-16 17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "审核所需要的参数")
public class AuditDto {

    @ApiModelProperty(value = "审核对象的主键id")
    private Long id;

    @ApiModelProperty(value = "审核更新的状态：审核通过/审核不通过")
    private String isPassAudit;

    @ApiModelProperty(value = "审核所要给的原因")
    private String auditReason;
}
