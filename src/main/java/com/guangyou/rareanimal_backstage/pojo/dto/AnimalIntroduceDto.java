package com.guangyou.rareanimal_backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-03-29 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "动物信息介绍")
public class AnimalIntroduceDto {

    @ApiModelProperty(value = "动物id")
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
