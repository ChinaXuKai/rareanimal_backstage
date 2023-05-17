package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 15:13
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据圈子id 分页查询对应文章 分页
     * @param categoryId 圈子id
     * @param initialDataLocation 初始数据位置
     * @param pageSize 每页数据多少
     * @return
     */
    List<Article> getArticlesByCategory(Long categoryId, Integer initialDataLocation, Integer pageSize);

    /**
     * 根据文章id 逻辑删除文章
     * @param articleId 文章id
     * @return 逻辑删除结果
     */
    int deleteArticleByAid(Long articleId);

}
