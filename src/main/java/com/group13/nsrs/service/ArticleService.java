package com.group13.nsrs.service;

import com.group13.nsrs.model.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.util.Result;

import java.util.List;

/**
* @author Oreki
* @description 针对表【article】的数据库操作Service
* @createDate 2023-08-20 11:00:40
*/
public interface ArticleService extends IService<Article> {

    Result<List<Article>> getOwnArticles();

    Result<List<Article>> getArticlesByAuthorId(Long authorId);
}
