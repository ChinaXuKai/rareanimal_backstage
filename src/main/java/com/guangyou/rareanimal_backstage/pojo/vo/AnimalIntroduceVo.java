package com.guangyou.rareanimal_backstage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xukai
 * @create 2023-04-08 22:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "动物信息的详情描述")
public class AnimalIntroduceVo {

    @ApiModelProperty(value = "主键Id")
    private Integer animalId;

    @ApiModelProperty(value = "形态特征")
    private String morphology;

    @ApiModelProperty(value = "栖息环境")
    private String habitat;

    @ApiModelProperty(value = "生活习性")
    private String habits;

    @ApiModelProperty(value = "种群现状")
    private String populationStatus;

    @ApiModelProperty(value = "保护等级")
    private String protectionLevel;

    @ApiModelProperty(value = "其他相关信息")
    private String elseInfo;

}
