package com.guangyou.rareanimal_backstage.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-09 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "动物标签的信息")
public class AnimalLabelVo {

    @ApiModelProperty(value = "动物id")
    private Long animalId;

    @ApiModelProperty(value = "动物标签集合")
    private String animalLabel;

}
