package com.group13.nsrs.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.dto.CommentSaveDto;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.entity.Comment;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.model.vo.CommentVo;
import com.group13.nsrs.service.ArticleService;
import com.group13.nsrs.service.CommentService;
import com.group13.nsrs.mapper.CommentMapper;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Oreki
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2023-08-21 10:13:19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Override
    public Result<Long> saveComment(CommentSaveDto commentSaveDto) {
        Long articleId = commentSaveDto.getArticleId();
        if (articleId == null) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR, "文章id不能为空");
        }
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        Article article = articleService.getById(articleId);
        if (article == null) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR, "文章不存在");
        }
        Comment comment = BeanUtil.copyProperties(commentSaveDto, Comment.class);
        comment.setUserId(user.getId());
        this.save(comment);
        return Result.ok(comment.getId());
    }

    @Override
    public Result<List<CommentVo>> listCommentsByArticleId(Long articleId) {
        List<Comment> comments = this.lambdaQuery().eq(Comment::getArticleId, articleId).list();
        List<CommentVo> commentVos = comments.stream().map(comment -> {
            CommentVo commentVo = BeanUtil.copyProperties(comment, CommentVo.class);
            Long userId = comment.getUserId();
            User user = userService.getById(userId);
            commentVo.setUserName(user.getName());
            commentVo.setUserAvatar(user.getAvatar());
            return commentVo;
        }).collect(Collectors.toList());
        return Result.ok(commentVos);
    }
}




