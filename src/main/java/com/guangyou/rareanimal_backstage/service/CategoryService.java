package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.CategoryDto;
import com.guangyou.rareanimal_backstage.pojo.entity.CategoryTheme;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleCategoriesVo;

import java.util.List;

/**
 * @author xukai
 * @create 2023-05-18 10:10
 */
public interface CategoryService {

    /**
     * 获取所有的圈子信息
     * @return 结果集
     */
    Result getAllCategory();

    /**
     * 获取所有圈子的主题
     * @return
     */
    List<CategoryTheme> getAllTheme();

    /**
     * 根据主题id获取对应的圈子
     * @param themeId
     * @return
     */
    ArticleCategoriesVo getCategoryByThemeId(Long themeId);

    /**
     * 新增圈子
     * @param categoryDto 新增圈子所需要的参数
     * @return 结果集
     */
    Result addNewCategory(CategoryDto categoryDto);

    /**
     * 修改圈子
     * @param categoryDto 修改圈子所需要的参数
     * @return 结果集
     */
    Result updateCategory(CategoryDto categoryDto);
}
