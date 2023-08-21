package com.group13.nsrs.service;

import com.group13.nsrs.model.dto.CommentSaveDto;
import com.group13.nsrs.model.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.model.vo.CommentVo;
import com.group13.nsrs.util.result.Result;

import java.util.List;

/**
* @author Oreki
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-08-21 10:13:20
*/
public interface CommentService extends IService<Comment> {

    Result<Long> saveComment(CommentSaveDto commentSaveDto);

    Result<List<CommentVo>> listCommentsByArticleId(Long articleId);
}
