package com.guangyou.rareanimal_backstage.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.guangyou.rareanimal_backstage.pojo.entity.AnimalIntroduce;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-08 22:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "动物用以展示的相关数据")
public class AnimalVo {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "动物主键id")
    private Long animalId;

    @ApiModelProperty(value = "动物名称")
    private String animalName;

    @ApiModelProperty(value = "动物别名")
    private String alias;

    @ApiModelProperty(value = "动物图片，用于AR扫描")
    private String animalImg;

    @ApiModelProperty(value = "是否为脊椎动物：1为脊椎动物，0为非脊椎动物")
    private Integer isVertebrates;

    @ApiModelProperty(value = "是否为哺乳动物：1为哺乳动物，0为非哺乳动物")
    private Integer isSuckle;

    @ApiModelProperty(value = "是否为鸟类动物：1为鸟类动物，0为非鸟类动物")
    private Integer isBirds;

    @ApiModelProperty(value = "是否为爬行动物：1为爬行动物，0为非爬行动物")
    private Integer isCreep;

    @ApiModelProperty(value = "是否为鱼类动物：1为鱼类动物，0为非鱼类动物")
    private Integer isFish;

    @ApiModelProperty(value = "是否为两栖类动物：1为两栖类动物，0为非两栖类动物")
    private Integer isAmphibian;

    @ApiModelProperty(value = "生物分类：界门纲目科属种")
    private String animalClassification;

    @ApiModelProperty(value = "分布区域")
    private String distributionArea;

    @ApiModelProperty(value = "保护等级")
    private String protectGrade;

    @ApiModelProperty(value = "动物的简介")
    private AnimalIntroduceVo animalIntroduce;

    @ApiModelProperty(value = "动物标签")
    private List<String> animalLabel;

}
