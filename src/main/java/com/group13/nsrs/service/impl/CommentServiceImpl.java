package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.entity.Comment;
import com.group13.nsrs.service.CommentService;
import com.group13.nsrs.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Oreki
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-08-21 10:13:19
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




