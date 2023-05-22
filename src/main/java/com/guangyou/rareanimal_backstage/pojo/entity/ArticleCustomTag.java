package com.guangyou.rareanimal_backstage.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xukai
 * @create 2023-05-22 12:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCustomTag {

    private Long id;

    private String customTagName;

    private Long articleId;

}
