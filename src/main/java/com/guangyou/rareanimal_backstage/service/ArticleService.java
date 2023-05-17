package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.AuditDto;
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

    /**
     * 根据文章id 逻辑删除文章
     * @param articleId 文章id
     * @return 结果集
     */
    Result deleteArticleByAid(Long articleId);

    /**
     * 审核文章
     * @param auditDto 审核所需要的参数
     * @return 更新审核所影响的数据条数
     */
    int auditArticle(AuditDto auditDto);

}
