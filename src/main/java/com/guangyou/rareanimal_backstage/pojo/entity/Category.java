package com.guangyou.rareanimal_backstage.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xukai
 * @create 2023-02-10 14:36
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long themeId;

    private String categoryLabel;

    private String categoryName;

    private Long articleCount;

    private String categoryAvatar;

}
