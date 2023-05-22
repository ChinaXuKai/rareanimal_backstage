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

    /**
     * 获取圈子 分页数据集
     * @param initialDataLocation 初始数据位置
     * @param pageSize 每页多少数据
     * @param themeId 主题id
     * @return 圈子分页数据集
     */
    List<CategoryVo> getCategoryByPageAndId(Integer initialDataLocation,Integer pageSize, Long themeId);


}
