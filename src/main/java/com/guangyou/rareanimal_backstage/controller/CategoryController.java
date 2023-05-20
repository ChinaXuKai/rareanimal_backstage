package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.CategoryDto;
import com.guangyou.rareanimal_backstage.pojo.entity.CategoryTheme;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleCategoriesVo;
import com.guangyou.rareanimal_backstage.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xukai
 * @create 2023-05-18 11:18
 */
@Slf4j
@RestController
@RequestMapping("/category")
@Api(tags = "管理圈子接口")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取文章圈子的主题集合",notes = "获取文章圈子的主题集合")
    @GetMapping("/getCategoryTheme")
    public Result getCategoryTheme(){
        List<CategoryTheme> categoryThemes = categoryService.getAllTheme();
        return Result.succ(200,"",categoryThemes);
    }


    @ApiOperation(value = "获取主题id对应的圈子集合",notes = "获取主题id对应的文章圈子集合")
    @GetMapping("/getArticleCategory/{themeId}")
    public Result getArticleCategory(@PathVariable("themeId") Long themeId){
        ArticleCategoriesVo articleCategories = categoryService.getCategoryByThemeId(themeId);
        return Result.succ(200, "请在下列文章圈子中选择一样", articleCategories);
    }


    @ApiOperation(value = "新增圈子")
    @PostMapping("/addNewCategory")
    public Result addNewCategory(@RequestBody @Validated CategoryDto categoryDto){
        return categoryService.addNewCategory(categoryDto);
    }


    @ApiOperation(value = "修改圈子")
    @PutMapping("/updateCategory")
    public Result updateCategory(@RequestBody @Validated CategoryDto categoryDto){
        return categoryService.updateCategory(categoryDto);
    }


}
