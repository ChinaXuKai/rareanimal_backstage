package com.guangyou.rareanimal_backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author xukai
 * @create 2023-03-29 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "动物的基本信息")
public class AnimalBasicInfoDto {

    @NotBlank
    @ApiModelProperty(value = "动物名称")
    private String animalName;

    @NotBlank
    @ApiModelProperty(value = "动物别名")
    private String animalAlias;

    @NotBlank
    @ApiModelProperty(value = "动物图片URL",notes = "传一个动物图片URL 作为AR扫图使用 并 作为页面展示")
    private String animalImgUrl;

    @NotBlank
    @ApiModelProperty(value = "动物具体分类",notes = "动物具体分类：界门纲目科属种")
    private String animalSpecificType;

    @NotBlank
    @ApiModelProperty(value = "分布区域")
    private String distributionArea;

    @NotBlank
    @ApiModelProperty(value = "保护等级")
    private String protectGrade;

}
