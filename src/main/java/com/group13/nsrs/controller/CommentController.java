package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.CommentSaveDto;
import com.group13.nsrs.model.entity.Comment;
import com.group13.nsrs.model.vo.CommentVo;
import com.group13.nsrs.service.CommentService;
import com.group13.nsrs.util.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping
    public Result<Long> saveComment(@RequestBody CommentSaveDto commentSaveDto) {
        return commentService.saveComment(commentSaveDto);
    }

    @GetMapping("/{articleId}")
    public Result<List<CommentVo>> listCommentsByArticleId(@PathVariable Long articleId) {
        return commentService.listCommentsByArticleId(articleId);
    }
}
