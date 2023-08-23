package com.group13.nsrs.service.impl;

import java.time.LocalDateTime;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.constant.ArticleConstants;
import com.group13.nsrs.model.dto.ArticleDto;
import com.group13.nsrs.model.entity.Article;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.model.vo.ArticleVo;
import com.group13.nsrs.model.vo.BehaviorVo;
import com.group13.nsrs.model.vo.HotArticleVo;
import com.group13.nsrs.service.ArticleService;
import com.group13.nsrs.mapper.ArticleMapper;
import com.group13.nsrs.service.BehaviorService;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.util.redis.CacheService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Oreki
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2023-08-20 11:00:40
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public Result<List<ArticleVo>> getOwnArticles() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            // 未登录
            return Result.ok();
        }
        List<Article> articles = this.lambdaQuery().eq(Article::getAuthorId, user.getId()).orderByDesc(Article::getCreatedTime).list();
        List<ArticleVo> articleVos = packageArticles(articles);
        return Result.ok(articleVos);
    }

    @Override
    public Result<List<ArticleVo>> getArticlesByAuthorId(Long authorId) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            // 未登录
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        List<Article> articles = this.lambdaQuery().eq(Article::getAuthorId, authorId).orderByDesc(Article::getCreatedTime).list();
        List<ArticleVo> articleVos = packageArticles(articles);
        return Result.ok(articleVos);
    }

    @Override
    public Result<Long> saveArticle(ArticleDto articleDto) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            // 未登录
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        Article article = BeanUtil.copyProperties(articleDto, Article.class);
        article.setAuthorId(user.getId());
        article.setLikes(0);
        article.setCollection(0);
        article.setComment(0);
        article.setViews(0);
        this.save(article);
        return Result.ok(article.getId());
    }

    @Resource
    private CacheService cacheService;

    // 每小时执行一次
    @Scheduled(cron = "0 0 */1 * * ?")
    @Override
    public void computeHotArticle() {
        // 查询五天内的所有文章
        LocalDateTime dayParam = LocalDateTime.now().minusDays(5);
        List<ArticleVo> articleVos = this.listArticles("").getData();
        articleVos = articleVos.stream().filter(articleVo -> articleVo.getCreatedTime().isAfter(dayParam)).collect(Collectors.toList());
        // 计算文章热度
        List<HotArticleVo> hotArticleVos = computeHotArticle(articleVos);
        // 取出前三十缓存到redis
        String key = ArticleConstants.HOT_ARTICLE_KEY;
        hotArticleVos.stream()
                .sorted(Comparator.comparing(HotArticleVo::getScore).reversed()).limit(30)
                .forEach(hotArticleVo -> cacheService.zAdd(key, hotArticleVo.getId().toString(), hotArticleVo.getScore()));
    }

    private List<HotArticleVo> computeHotArticle(List<ArticleVo> articles) {
        return articles.stream().map(articleVo -> {
            HotArticleVo hotArticleVo = BeanUtil.copyProperties(articleVo, HotArticleVo.class);
            hotArticleVo.setScore(computeScore(hotArticleVo));
            return hotArticleVo;
        }).collect(Collectors.toList());
    }

    private Integer computeScore(HotArticleVo hotArticleVo) {
        int score = 0;
        // 1.阅读数
        if (hotArticleVo.getViews() != null) {
            score += hotArticleVo.getViews();
        }
        // 2.点赞数
        if (hotArticleVo.getLikes() != null) {
            score += hotArticleVo.getLikes() * ArticleConstants.HOT_ARTICLE_LIKE_WEIGHT;
        }
        // 3.评论数
        if (hotArticleVo.getComment() != null) {
            score += hotArticleVo.getComment() * ArticleConstants.HOT_ARTICLE_COMMENT_WEIGHT;
        }
        // 4.收藏数
        if (hotArticleVo.getCollection() != null) {
            score += hotArticleVo.getCollection() * ArticleConstants.HOT_ARTICLE_COLLECTION_WEIGHT;
        }
        return score;
    }

    @Override
    @Async
    public void incrComment(Long id) {
        Article article = this.getById(id);
        if (article == null) {
            return;
        }
        article.setComment(article.getComment() + 1);
        this.updateById(article);
    }

    @Override
    @Async
    public void updateLikes(Long articleId, Integer incr) {
        Article article = this.getById(articleId);
        if (article == null) {
            return;
        }
        article.setLikes(article.getLikes() + incr);
        this.updateById(article);
    }

    @Override
    @Async
    public void updateCollection(Long articleId, Integer incr) {
        Article article = this.getById(articleId);
        if (article == null) {
            return;
        }
        article.setCollection(article.getCollection() + incr);
        this.updateById(article);
    }

    @Override
    @Async
    public void incrView(Long articleId) {
        Article article = this.getById(articleId);
        if (article == null) {
            return;
        }
        article.setViews(article.getViews() + 1);
        this.updateById(article);
    }

    @Resource
    private UserService userService;

    @Resource
    @Lazy
    private BehaviorService behaviorService;

    @Override
    public Result<List<ArticleVo>> listArticles(String query) {
        List<Article> articles;
        if (StringUtils.isEmpty(query)) {
            // 查询所有文章
            articles = this.lambdaQuery().orderByDesc(Article::getCreatedTime).list();
        } else {
            // 模糊查询
            articles = this.lambdaQuery()
                    .like(Article::getTitle, query)
                    .or().like(Article::getContent, query)
                    .or().like(Article::getLabels, query)
                    .orderByDesc(Article::getCreatedTime)
                    .list();
        }
        List<ArticleVo> articleVos = packageArticles(articles);

        return Result.ok(articleVos);
    }

    @Override
    public List<ArticleVo> packageArticles(List<Article> articles) {
        return articles.stream().map(article -> {
            Long authorId = article.getAuthorId();
            User user = userService.getById(authorId);
            ArticleVo articleVo = BeanUtil.copyProperties(article, ArticleVo.class);
            if (user == null) {
                return articleVo;
            }
            articleVo.setAuthorName(user.getName());
            articleVo.setAuthorAvatar(user.getAvatar());
            setUserBehavior(articleVo);
            return articleVo;
        }).collect(Collectors.toList());
    }

    private void setUserBehavior(ArticleVo articleVo) {
        Result<BehaviorVo> res = behaviorService.getBehaviorInfo(articleVo.getId());
        if (!res.isOk()) {
            return;
        }
        BehaviorVo behaviorVo = res.getData();
        articleVo.setIsLiked(behaviorVo.getIsLiked());
        articleVo.setIsCollected(behaviorVo.getIsCollected());
    }

    @Override
    public Result<List<ArticleVo>> listHotArticles() {
        String key = ArticleConstants.HOT_ARTICLE_KEY;
        Set<String> hotArticleIds = cacheService.zReverseRange(key, 0, 30);
        List<Article> articles = hotArticleIds.stream()
                .map(hotArticleId -> this.getById(Long.parseLong(hotArticleId)))
                .collect(Collectors.toList());
        List<ArticleVo> articleVos = packageArticles(articles);
        return Result.ok(articleVos);
    }
}




