package com.group13.nsrs.service.impl;

import java.time.LocalDateTime;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.dto.ArticleDto;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.service.ArticleService;
import com.group13.nsrs.mapper.ArticleMapper;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oreki
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2023-08-20 11:00:40
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Override
    public Result<List<Article>> getOwnArticles() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            // 未登录
            return Result.ok();
        }
        return this.getArticlesByAuthorId(user.getId());
    }

    @Override
    public Result<List<Article>> getArticlesByAuthorId(Long authorId) {
        List<Article> articles = this.lambdaQuery().eq(Article::getAuthorId, authorId)
                .orderByDesc(Article::getCreatedTime).list();
        return Result.ok(articles);
    }

    @Override
    public Result<Long> saveArticle(ArticleDto articleDto) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            // 未登录
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        Article article = BeanUtil.copyProperties(articleDto, Article.class);
        article.setAuthorId(user.getId());
        article.setAuthorName(user.getName());
        article.setLikes(0);
        article.setCollection(0);
        article.setComment(0);
        article.setViews(0);
        this.save(article);
        return Result.ok(article.getId());
    }
}




