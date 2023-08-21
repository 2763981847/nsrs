package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.CommentSaveDto;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.vo.BehaviorVo;
import com.group13.nsrs.model.vo.CommentVo;
import com.group13.nsrs.service.BehaviorService;
import com.group13.nsrs.service.CommentService;
import com.group13.nsrs.util.result.Result;
import io.swagger.annotations.Api;
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
    public Result<BehaviorVo> getBehaviorInfo(@PathVariable Long articleId) {
        return behaviorService.getBehaviorInfo(articleId);
    }

    @GetMapping("/collection/{articleId}")
    public Result<List<Article>> getOwnCollections(@PathVariable Long articleId) {
        return behaviorService.getOwnCollections(articleId);
    }

    @PostMapping("/like/{articleId}")
    public Result<String> like(@PathVariable Long articleId) {
        return behaviorService.like(articleId);
    }

    @PostMapping("/collection/{articleId}")
    public Result<String> collection(@PathVariable Long articleId) {
        return behaviorService.collection(articleId);
    }

    @PostMapping("/view/{articleId}")
    public Result<String> view(@PathVariable Long articleId) {
        return behaviorService.view(articleId);
    }

    @Resource
    private CommentService commentService;

    @PostMapping("/comment")
    public Result<Long> saveComment(@RequestBody CommentSaveDto commentSaveDto) {
        return commentService.saveComment(commentSaveDto);
    }

    @GetMapping("/comment/{articleId}")
    public Result<List<CommentVo>> listCommentsByArticleId(@PathVariable Long articleId) {
        return commentService.listCommentsByArticleId(articleId);
    }
}
