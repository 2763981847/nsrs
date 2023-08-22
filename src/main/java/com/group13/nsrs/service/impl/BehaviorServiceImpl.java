package com.group13.nsrs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.group13.nsrs.constant.ArticleConstants;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.model.vo.ArticleVo;
import com.group13.nsrs.model.vo.BehaviorVo;
import com.group13.nsrs.service.ArticleService;
import com.group13.nsrs.service.BehaviorService;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.util.redis.CacheService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@Service
public class BehaviorServiceImpl implements BehaviorService {

    @Resource
    private ArticleService articleService;

    @Resource
    private CacheService cacheService;

    @Override
    public Result<String> like(Long articleId) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String key = ArticleConstants.USER_LIKES_KEY + user.getId();
        Boolean like = cacheService.sIsMember(key, articleId.toString());
        if (like != null && like) {
            // 取消点赞
            cacheService.sRemove(key, articleId.toString());
            articleService.updateLikes(articleId, -1);
        } else {
            // 点赞
            cacheService.sAdd(key, articleId.toString());
            articleService.updateLikes(articleId, 1);
        }
        return Result.ok();
    }

    @Override
    public Result<String> collection(Long articleId) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String key = ArticleConstants.USER_COLLECTION_KEY + user.getId();
        Boolean collection = cacheService.sIsMember(key, articleId.toString());
        if (collection != null && collection) {
            // 取消收藏
            cacheService.sRemove(key, articleId.toString());
            articleService.updateCollection(articleId, -1);
        } else {
            // 收藏
            cacheService.sAdd(key, articleId.toString());
            articleService.updateCollection(articleId, 1);
        }
        return Result.ok();
    }

    @Override
    public Result<String> view(Long articleId) {
        articleService.incrView(articleId);
        return Result.ok();
    }

    @Override
    public Result<BehaviorVo> getBehaviorInfo(Long articleId) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String likeKey = ArticleConstants.USER_LIKES_KEY + user.getId();
        String collectionKey = ArticleConstants.USER_COLLECTION_KEY + user.getId();
        BehaviorVo behaviorVo = new BehaviorVo();
        behaviorVo.setIsLiked(cacheService.sIsMember(likeKey, articleId.toString()));
        behaviorVo.setIsCollected(cacheService.sIsMember(collectionKey, articleId.toString()));
        return Result.ok(behaviorVo);
    }

    @Resource
    private UserService userService;

    @Override
    public Result<List<ArticleVo>> getOwnCollections() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String key = ArticleConstants.USER_COLLECTION_KEY + user.getId();
        Set<String> articleIds = cacheService.sMembers(key);
        List<Article> articles = articleService.listByIds(articleIds);
        List<ArticleVo> articleVos = articles.stream().map(article -> {
            ArticleVo articleVo = BeanUtil.copyProperties(article, ArticleVo.class);
            User author = userService.getById(articleVo.getAuthorId());
            articleVo.setAuthorAvatar(author.getAvatar());
            articleVo.setAuthorName(author.getName());
            return articleVo;
        }).collect(Collectors.toList());
        return Result.ok(articleVos);
    }
}
