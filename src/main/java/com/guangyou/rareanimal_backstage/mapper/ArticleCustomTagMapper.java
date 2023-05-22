package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.ArticleCustomTag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xukai
 * @create 2023-05-22 12:06
 */
@Repository
public interface ArticleCustomTagMapper extends BaseMapper<ArticleCustomTag> {

    /**
     * 根据 模糊查询条件 查询 动物id集合
     * @param articleLike 模糊查询条件
     * @return 动物id集合
     */
    List<Long> getArticleIdByLike(String articleLike);
}
