package com.guangyou.rareanimal_backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-03-29 10:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增或修改动物信息所需要的参数")
public class AnimalDto {

    @ApiModelProperty(value = "动物id",notes = "新增动物时不需要传值，修改动物时需要传值")
    private Integer animalId;

    @ApiModelProperty(value = "动物的基本信息")
    private AnimalBasicInfoDto animalBasicInfo;

    @ApiModelProperty(value = "动物种类",notes = "判断该动物是什么种类：无脊椎、鱼类等")
    private AnimalTypeDto animalType;

    @ApiModelProperty(value = "动物信息介绍")
    private AnimalIntroduceDto animalIntroduce;

    @ApiModelProperty(value = "动物标签")
    private AnimalLabelDto animalLabel;
}
