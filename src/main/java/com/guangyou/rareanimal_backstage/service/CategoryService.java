package com.guangyou.rareanimal_backstage.service;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.CategoryDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.CategoryTheme;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleCategoriesVo;
import com.guangyou.rareanimal_backstage.pojo.vo.CategoryVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;

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
     * @param pageDto 分页参数
     * @param themeId 主题id
     * @return
     */
    PageDataVo<CategoryVo> getArticleCategoryByPageAndId(PageDto pageDto, Long themeId);

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

    /**
     * 根据 圈子id 删除圈子
     * @param categoryId 圈子id
     * @return 结果集
     */
    Result deleteCategoryByCid(Long categoryId);

    /**
     * 根据主题id获取对应的圈子
     * @param themeId 主题id
     * @return
     */
    Result getArticleCategoryById(Long themeId);
}
