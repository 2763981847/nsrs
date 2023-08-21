package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.ArticleDto;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.vo.ArticleVo;
import com.group13.nsrs.service.ArticleService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation("保存(发布)文章")
    public Result<Long> saveArticle(@RequestBody
                                    @ApiParam(value = "待保存文章信息", required = true)
                                    ArticleDto articleDto) {
        return articleService.saveArticle(articleDto);
    }

    @GetMapping("/author/{authorId}")
    @ApiOperation("获取指定作者的文章列表")
    public Result<List<Article>> getArticlesByAuthorId(@PathVariable
                                                       @ApiParam(value = "作者id", required = true)
                                                       Long authorId) {
        return articleService.getArticlesByAuthorId(authorId);
    }

    @GetMapping("/own")
    @ApiOperation(value = "获取当前用户发布的文章列表", notes = "需要登录")
    public Result<List<Article>> getOwnArticles() {
        return articleService.getOwnArticles();
    }

    @GetMapping
    @ApiOperation(value = "获取所有文章列表", notes = "默认按发布时间倒序排列")
    public Result<List<ArticleVo>> listArticles() {
        return articleService.listArticles();
    }

}
