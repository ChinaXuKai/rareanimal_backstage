package com.guangyou.rareanimal_backstage.controller;

import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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



}
