package com.guangyou.rareanimal_backstage.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * @author xukai
 * @create 2023-03-29 13:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalTypeDto {
    //是
    private final static Integer IS = 1;
    //不是
    private final static Integer IS_NOT = 0;

    @ApiModelProperty(value = "是否为脊椎动物：1为脊椎动物，0为非脊椎动物")
    private Integer isVertebrates = IS;

    @ApiModelProperty(value = "是否为哺乳动物：1为哺乳动物，0为非哺乳动物")
    private Integer isSuckle = IS_NOT;

    @ApiModelProperty(value = "是否为鸟类动物：1为鸟类动物，0为非鸟类动物")
    private Integer isBirds = IS_NOT;

    @ApiModelProperty(value = "是否为爬行动物：1为爬行动物，0为非爬行动物")
    private Integer isCreep = IS_NOT;

    @ApiModelProperty(value = "是否为鱼类动物：1为鱼类动物，0为非鱼类动物")
    private Integer isFish = IS_NOT;

    @ApiModelProperty(value = "是否为两栖类动物：1为两栖类动物，0为非两栖类动物")
    private Integer isAmphibian = IS_NOT;

}
