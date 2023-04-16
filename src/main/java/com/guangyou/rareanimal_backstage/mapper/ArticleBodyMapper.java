package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.ArticleBody;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleBodyVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xukai
 * @create 2023-04-12 16:03
 */
@Mapper
public interface ArticleBodyMapper extends BaseMapper<ArticleBody> {
    /**
     * 根据 文章体id 获取文章体
     * @param bodyId 文章体id
     * @return 文章体vo
     */
    ArticleBodyVo findArticleBodyById(Long bodyId);
}
