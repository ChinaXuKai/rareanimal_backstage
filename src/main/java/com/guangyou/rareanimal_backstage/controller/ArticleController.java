package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.AuditDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.CategoryTheme;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleCategoriesVo;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.ArticleService;
import com.guangyou.rareanimal_backstage.service.CategoryService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xukai
 * @create 2023-04-12 14:57
 */
@Slf4j
@RestController
@RequestMapping("/article")
@Api(tags = "管理文章接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取圈子对应的文章分页数据集")
    @GetMapping("/getArticlesByPage")
    public Result getArticlesByPage(PageDto pageDto,Long categoryId){
        PageDataVo<ArticleVo> articleVoPageData = articleService.getArticlesByPage(pageDto,categoryId);

        if (articleVoPageData.getPageData().isEmpty()){
            return Result.succ("该圈子目前还没有文章");
        }else {
            return Result.succ(200, "该圈子文章有以下", articleVoPageData);
        }
    }


    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取所有的圈子")
    @GetMapping("/getAllCategory")
    public Result getAllCategory(){
        return categoryService.getAllCategory();
    }


    @ApiOperation(value = "删除文章")
    @DeleteMapping("/deleteArticleByAid")
    public Result deleteArticleByAid(Long ArticleId){
        return articleService.deleteArticleByAid(ArticleId);
    }


    @ApiOperation(value = "审核文章")
    @PutMapping("/auditArticle")
    public Result auditArticle( AuditDto auditDto){
        int auditResult = articleService.auditArticle(auditDto);
        if (auditResult == 0) {
            return Result.fail("审核文章出现异常");
        }
        return Result.succ( "审核成功，文章id为：" + auditDto.getId());
    }

}
