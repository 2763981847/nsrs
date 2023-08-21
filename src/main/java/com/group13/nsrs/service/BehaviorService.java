package com.group13.nsrs.service;

import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.vo.BehaviorVo;
import com.group13.nsrs.util.result.Result;

import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
public interface BehaviorService {
    Result<String> like(Long articleId);

    Result<String> collection(Long articleId);

    Result<String> view(Long articleId);

    Result<BehaviorVo> getBehaviorInfo(Long articleId);

    Result<List<Article>> getOwnCollections();
}
