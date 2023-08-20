package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.service.ArticleService;
import com.group13.nsrs.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author Oreki
* @description 针对表【article】的数据库操作Service实现
* @createDate 2023-08-20 11:00:40
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




