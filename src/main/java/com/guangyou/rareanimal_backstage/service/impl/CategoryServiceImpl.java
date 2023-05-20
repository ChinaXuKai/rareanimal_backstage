package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.mapper.CategoryMapper;
import com.guangyou.rareanimal_backstage.mapper.CategoryThemeMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.CategoryDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Category;
import com.guangyou.rareanimal_backstage.pojo.entity.CategoryTheme;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleCategoriesVo;
import com.guangyou.rareanimal_backstage.pojo.vo.CategoryVo;
import com.guangyou.rareanimal_backstage.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xukai
 * @create 2023-05-18 10:10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result getAllCategory() {
        List<Category> categoryList = categoryMapper.selectList(null);

        if (categoryList.isEmpty()){
            return Result.fail("获取圈子出现异常");
        }

        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVoList.add(categoryVo);
        }
        return Result.succ(categoryList);
    }


    @Autowired
    private CategoryThemeMapper categoryThemeMapper;

    @Override
    public List<CategoryTheme> getAllTheme() {
        return categoryThemeMapper.selectList(null);
    }

    @Override
    public ArticleCategoriesVo getCategoryByThemeId(Long themeId) {
        ArticleCategoriesVo articleCategoriesVo = new ArticleCategoriesVo();
        List<CategoryVo> categoryVos = categoryMapper.selectCategoryByThemeId(themeId);
        articleCategoriesVo.setThemeId(themeId);
        CategoryTheme theme = categoryThemeMapper.selectOne(new LambdaQueryWrapper<CategoryTheme>()
                .eq(CategoryTheme::getId,themeId));
        articleCategoriesVo.setTheme(theme.getThemeName());
        articleCategoriesVo.setArticleCategories(categoryVos);
        return articleCategoriesVo;
    }


    @Override
    public Result addNewCategory(CategoryDto categoryDto) {
        //若所选的主题id 不存在则报错
        Long themeId = categoryDto.getThemeId();
        CategoryTheme categoryTheme = categoryThemeMapper.selectOne(new LambdaQueryWrapper<CategoryTheme>()
                .eq(CategoryTheme::getId, themeId));
        if (categoryTheme == null) {
            return Result.fail(Result.FORBIDDEN,"当前所选的主题id不存在，请重新选择",null);
        }
        //新增圈子
        Category category = new Category();
        category.setThemeId(themeId);
        category.setCategoryAvatar(categoryDto.getCategoryAvatar());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryLabel(categoryDto.getCategoryLabel());
        category.setArticleCount(0L);
        int insertResult = categoryMapper.insert(category);
        if (insertResult == 0){
            return Result.fail("新增圈子失败");
        }
        return Result.succ(200, "新增圈子成功", category.getId());
    }


    @Override
    public Result updateCategory(CategoryDto categoryDto) {
        //根据主键id 修改圈子
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Category::getId,categoryDto.getId());
        updateWrapper.set(Category::getThemeId, categoryDto.getThemeId());
        updateWrapper.set(Category::getCategoryName, categoryDto.getCategoryName());
        updateWrapper.set(Category::getCategoryLabel, categoryDto.getCategoryLabel());
        updateWrapper.set(Category::getCategoryAvatar, categoryDto.getCategoryAvatar());
        int updateResult = categoryMapper.update(null, updateWrapper);
        if (updateResult == 0) {
            return Result.fail("修改圈子失败");
        }
        return Result.succ(200, "修改圈子成功", categoryDto.getId());
    }
}
