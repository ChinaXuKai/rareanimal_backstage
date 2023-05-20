package com.guangyou.rareanimal_backstage.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author xukai
 * @create 2023-03-29 14:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "动物标签")
public class AnimalLabelDto {

    @NotEmpty
    @ApiModelProperty(value = "动物标签集合")
    private List<String> animalLabelList;
}
