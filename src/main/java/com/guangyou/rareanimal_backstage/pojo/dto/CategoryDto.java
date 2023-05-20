package com.guangyou.rareanimal_backstage.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-05-20 9:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "新增或修改圈子所需要的参数")
public class CategoryDto {

    @ApiModelProperty(value = "该圈子的主键id",notes = "新增圈子时不需要传该值，修改圈子时需要传该值")
    private Long id;

    @ApiModelProperty(value = "该圈子所属的主题id")
    private Long themeId;

    @ApiModelProperty(value = "圈子名称")
    private String categoryName;

    @ApiModelProperty(value = "圈子标签")
    private String categoryLabel;

    @ApiModelProperty(value = "圈子图标")
    private String categoryAvatar;

}
