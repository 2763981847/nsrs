package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.CommentSaveDto;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.vo.ArticleVo;
import com.group13.nsrs.model.vo.BehaviorVo;
import com.group13.nsrs.model.vo.CommentVo;
import com.group13.nsrs.service.BehaviorService;
import com.group13.nsrs.service.CommentService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@RestController
@RequestMapping("/api/behavior")
@Api(tags = "用户行为接口")
public class BehaviorController {
    @Resource
    private BehaviorService behaviorService;

    @GetMapping("/{articleId}")
    @ApiOperation(value = "获取当前用户对指定文章的行为信息", notes = "获取当前登录用户对指定文章是否点赞，是否收藏")
    public Result<BehaviorVo> getBehaviorInfo(@PathVariable
                                              @ApiParam(value = "文章id", required = true)
                                              Long articleId) {
        return behaviorService.getBehaviorInfo(articleId);
    }

    @GetMapping("/collection")
    @ApiOperation(value = "获取当前登录用户收藏的文章列表")
    public Result<List<ArticleVo>> getOwnCollections() {
        return behaviorService.getOwnCollections();
    }

    @PostMapping("/like/{articleId}")
    @ApiOperation(value = "点赞（或取消点赞）指定文章")
    public Result<String> like(@PathVariable Long articleId) {
        return behaviorService.like(articleId);
    }

    @PostMapping("/collection/{articleId}")
    @ApiOperation(value = "收藏（或取消收藏）指定文章")
    public Result<String> collection(@PathVariable Long articleId) {
        return behaviorService.collection(articleId);
    }

    @PostMapping("/view/{articleId}")
    @ApiOperation(value = "浏览指定文章")
    public Result<String> view(@PathVariable Long articleId) {
        return behaviorService.view(articleId);
    }

    @Resource
    private CommentService commentService;

    @PostMapping("/comment")
    @ApiOperation(value = "保存（发布）评论")
    public Result<Long> saveComment(@RequestBody
                                    @ApiParam(value = "待保存评论信息", required = true)
                                    CommentSaveDto commentSaveDto) {
        return commentService.saveComment(commentSaveDto);
    }

    @GetMapping("/comment/{articleId}")
    @ApiOperation(value = "获取指定文章的评论列表", notes = "返回的评论列表中封装了评论者的头像和昵称信息")
    public Result<List<CommentVo>> listCommentsByArticleId(@PathVariable
                                                           @ApiParam(value = "文章id", required = true)
                                                           Long articleId) {
        return commentService.listCommentsByArticleId(articleId);
    }
}
