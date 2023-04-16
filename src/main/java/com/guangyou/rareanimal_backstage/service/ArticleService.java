package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 15:05
 */
public interface ArticleService {
    /**
     * 根据圈子获取对应的文章 分页数据集
     * @param pageDto 分页条件
     * @param categoryId 圈子id
     * @return 分页数据集
     */
    PageDataVo<ArticleVo> getArticlesByPage(PageDto pageDto, Long categoryId);
}
