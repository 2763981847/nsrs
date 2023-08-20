package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.ArticleDto;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.service.ArticleService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@RestController
@RequestMapping("/api/article")
@Api(tags = "学生社区文章接口")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    public Result<Long> saveArticle(@RequestBody ArticleDto articleDto) {
        return Result.ok();
    }

    @GetMapping("/author/{authorId}")
    public Result<List<Article>> getArticlesByAuthorId(@PathVariable Long authorId) {
        return articleService.getArticlesByAuthorId(authorId);
    }

    @GetMapping("/own")
    public Result<List<Article>> getOwnArticles() {
        return articleService.getOwnArticles();
    }

    @GetMapping
    public Result<List<Article>> listArticles() {
        return Result.ok(articleService.lambdaQuery().orderByDesc(Article::getCreatedTime).list());
    }
}
