package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.mapper.CategoryMapper;
import com.guangyou.rareanimal_backstage.mapper.CategoryThemeMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.CategoryDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Category;
import com.guangyou.rareanimal_backstage.pojo.entity.CategoryTheme;
import com.guangyou.rareanimal_backstage.pojo.entity.Opinion;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleCategoriesVo;
import com.guangyou.rareanimal_backstage.pojo.vo.CategoryVo;
import com.guangyou.rareanimal_backstage.pojo.vo.OpinionVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.CategoryService;
import com.guangyou.rareanimal_backstage.utils.CopyUtils;
import net.sf.saxon.expr.instruct.Copy;
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
    public  PageDataVo<CategoryVo> getArticleCategoryByPageAndId(PageDto pageDto, Long themeId) {
        PageDataVo<CategoryVo> pageDataVo = new PageDataVo<>();
        List<CategoryVo> categoryVos = categoryMapper.getCategoryByPageAndId(pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize(), themeId);

        pageDataVo.setPageData(categoryVos);
        //设置 数据库中 所属主题下的圈子总数（total）、每页显示数量（size）、当前第几页（current）、总共有多少页数据（pages）
        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());
        int total = categoryMapper.selectCount(new LambdaQueryWrapper<Category>()
                .eq(Category::getThemeId,themeId)).intValue();
        pageDataVo.setTotal(total);
        int isRemainZero = total%pageDto.getPageSize();
        if (isRemainZero != 0){
            pageDataVo.setPages( (total/pageDto.getPageSize()) + 1);
        }else {
            pageDataVo.setPages( total/pageDto.getPageSize() );
        }

        return pageDataVo;
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


    @Override
    public Result deleteCategoryByCid(Long categoryId) {
        //根据圈子id删除圈子
        int deleteResult = categoryMapper.delete(new LambdaUpdateWrapper<Category>()
                .eq(Category::getId, categoryId));
        if (deleteResult == 0) {
            return Result.fail("删除圈子失败");
        }
        return Result.succ(categoryId);
    }


    @Override
    public Result getArticleCategoryById(Long themeId) {
        ArticleCategoriesVo articleCategoriesVo = new ArticleCategoriesVo();
        //查询themeId对应的主题
        CategoryTheme categoryTheme = categoryThemeMapper.selectOne(new LambdaQueryWrapper<CategoryTheme>()
                .eq(CategoryTheme::getId, themeId));
        articleCategoriesVo.setTheme(categoryTheme.getThemeName());
        articleCategoriesVo.setThemeId(categoryTheme.getId());
        //查询themeId对应
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getThemeId, themeId);
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);
        //将 categoryList 赋值为 categoryVoList
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVoList.add(categoryVo);
        }
        articleCategoriesVo.setArticleCategories(categoryVoList);

        if (articleCategoriesVo.getArticleCategories() == null) {
            return Result.fail("根据主题id查询对应圈子失败，主题id为：" + themeId);
        }
        return Result.succ(200, "请在下列文章圈子中选择一样", articleCategoriesVo);
    }
}
