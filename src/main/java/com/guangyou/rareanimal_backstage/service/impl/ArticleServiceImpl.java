package com.guangyou.rareanimal_backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.guangyou.rareanimal_backstage.common.lang.Result;
import com.guangyou.rareanimal_backstage.mapper.ArticleCustomTagMapper;
import com.guangyou.rareanimal_backstage.mapper.ArticleMapper;
import com.guangyou.rareanimal_backstage.pojo.dto.AuditDto;
import com.guangyou.rareanimal_backstage.pojo.dto.PageDto;
import com.guangyou.rareanimal_backstage.pojo.entity.Article;
import com.guangyou.rareanimal_backstage.pojo.entity.Category;
import com.guangyou.rareanimal_backstage.pojo.vo.ArticleVo;
import com.guangyou.rareanimal_backstage.pojo.vo.PageDataVo;
import com.guangyou.rareanimal_backstage.service.ArticleService;
import com.guangyou.rareanimal_backstage.utils.CopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//        log.info("之前的报错异常：{}","\\n### Error querying database. Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '-3,3' at line 6\\n### The error may exist in URL [jar:file:/usr/rareanimalProject_backstage/rareanimal_backstage-1.0-SNAPSHOT.jar!/BOOT-INF/classes!/mapper/ArticleMapper.xml]\\n### The error may involve defaultParameterMap\\n### The error occurred while setting parameters\\n### SQL: SELECT * FROM t_article WHERE category_id = ? and is_delete = 0 and audit_state = '审核不通过' LIMIT ?,?\\n### Cause: java.");
//        log.info("\n\n");

        //根据圈子id 分页查询对应文章 分页
        List<Article> articleList = articleMapper.getArticlesByCategory(categoryId,pageDto.getPageSize() * (pageDto.getPage() - 1), pageDto.getPageSize());
        //转换为 articleVo 集合
        List<ArticleVo> articleVoList = copyUtils.articleListCopy(articleList, true, true, true, true);
        //为 pageDataVo 赋值
        pageDataVo.setPageData(articleVoList);
        pageDataVo.setCurrent(pageDto.getPage());
        pageDataVo.setSize(pageDto.getPageSize());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getAuditState, Article.WAIT_AUDIT);
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


    @Autowired
    private ArticleCustomTagMapper articleCustomTagMapper;

    @Override
    public Result getArticleByLike(String articleLike) {
        //1.1 先根据 articleLike 模糊查询 文章标签，获取到相应的 articleId 集合
        List<Long> articlesIdByTag = articleCustomTagMapper.getArticleIdByLike("%"+articleLike+"%");
        //1.2 再根据 articleLike 模糊查询 文章标题，获取相应的 article 集合
        List<Long> articlesIdByTitle = articleMapper.getArticleIdByLike("%"+articleLike+"%");
        //1.3 将上述两个 文章id集合 合并成一个 id集合
        List<Long> articlesId = new ArrayList<>();
        articlesId.addAll(articlesIdByTag);
        articlesId.addAll(articlesIdByTitle);
        //2 若此时 articlesId 的元素个数为0，则返回 Result
        if (articlesId.isEmpty()){
            return Result.succ("当前查询结果为 0 个数据");
        }

        //3 for循环遍历articlesId，根据 articleId 查询对应 article
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Long articleId : articlesId){
            Article article = articleMapper.selectOne(
                    new LambdaQueryWrapper<Article>().eq(Article::getId, articleId));
            //若article 的 审核状态为 "待审核"，则转为 articleVo
            if (Article.WAIT_AUDIT.equals(article.getAuditState())){
                articleVoList.add(copyUtils
                        .articleCopy(article, false, true, true, true));
            }
        }

        //4 返回 articleVo
        return Result.succ(200,"根据'"+articleLike+"'查询的结果如下：",articleVoList);
    }


}
