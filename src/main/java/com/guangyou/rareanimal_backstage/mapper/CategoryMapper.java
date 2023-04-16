package com.guangyou.rareanimal_backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guangyou.rareanimal_backstage.pojo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

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
}
