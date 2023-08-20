package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.ArticleDto;
import com.group13.nsrs.model.dto.LoginVo;
import com.group13.nsrs.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @PostMapping
    public Result<Long> saveArticle(ArticleDto articleDto) {
        return Result.ok();
    }
}
