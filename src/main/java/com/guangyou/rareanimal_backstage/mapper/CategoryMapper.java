package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.Category;
import com.guangyou.rareanimal_backstage.pojo.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 16:06
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 根据圈子id 获取圈子信息
     * @param categoryId 圈子id
     * @return 圈子
     */
    Category findCategoryById(Long categoryId);

    //根据文章圈子主题的id查询对应文章圈子
    List<CategoryVo> selectCategoryByThemeId(Long themeId);


}
