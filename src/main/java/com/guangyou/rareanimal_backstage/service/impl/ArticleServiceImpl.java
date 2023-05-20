package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.mapper.ArticleMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.AuditDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Article;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.ArticleService;
import com.guangyou.rareanimal_backstage.utils.CopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xukai
 * @create 2023-04-12 15:05
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CopyUtils copyUtils;

    @Override
    public PageDataVo<ArticleVo> getArticlesByPage(PageDto pageDto, Long categoryId) {
        PageDataVo<ArticleVo> pageDataVo = new PageDataVo<>();
        //根据圈子id 分页查询对应文章 分页
        List<Article> articleList = articleMapper.getArticlesByCategory(categoryId,pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize());

        List<ArticleVo> articleVoList = copyUtils.articleListCopy(articleList, true, true, true, true);
        pageDataVo.setPageData(articleVoList);

        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId, categoryId);
        int total = articleMapper.selectCount(queryWrapper).intValue();
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
    public Result deleteArticleByAid(Long articleId) {
        int deleteResult = articleMapper.deleteArticleByAid(articleId);
        if (deleteResult == 0){
            return Result.fail("删除文章失败");
        }
        return Result.succ(200, "删除文章成功", articleId);
    }


    @Override
    public int auditArticle(AuditDto auditDto) {
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, auditDto.getId());
        updateWrapper.set(Article::getAuditState, auditDto.getIsPassAudit());
        updateWrapper.set(Article::getAuditReason, auditDto.getAuditReason());
        return articleMapper.update(null, updateWrapper);
    }


}
