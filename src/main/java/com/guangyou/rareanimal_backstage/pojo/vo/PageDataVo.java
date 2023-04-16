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
 * @create 2023-04-08 22:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "分页数据的模版")
public class PageDataVo<T> {

    @ApiModelProperty(value = "分页后的数据集")
    private List<T> pageData;

    @ApiModelProperty(value = "数据集的总个数")
    private Integer total;

    @ApiModelProperty(value = "每页显示的个数")
    private Integer size;

    @ApiModelProperty(value = "第几页")
    private Integer current;

    @ApiModelProperty(value = "总多少页")
    private Integer pages;

}