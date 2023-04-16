package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.CustomTag;
import com.guangyou.rareanimal_backstage.pojo.vo.CustomTagVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 15:56
 */
@Mapper
public interface CustomTagMapper extends BaseMapper<CustomTag> {
    /**
     * 根据文章id 查询该文章的标签
     * @param articleId 文章id
     * @return 文章标签集合
     */
    List<CustomTagVo> selectTagsByArticleId(Long articleId);
}
