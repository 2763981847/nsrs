package com.group13.nsrs.constant;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
public class ArticleConstants {

    public static final String SERVER_NAME = "article";
    public static final String PREFIX = ProjectConstants.PROJECT_NAME + ":" + SERVER_NAME + ":";
    public static final String HOT_ARTICLE_KEY = PREFIX + "hotArticle:";
    public static final String USER_LIKES_KEY = PREFIX + "likes:";

    public static final String USER_COLLECTION_KEY = PREFIX + "collection:";
    public static final Integer HOT_ARTICLE_LIKE_WEIGHT = 3;
    public static final Integer HOT_ARTICLE_COMMENT_WEIGHT = 5;
    public static final Integer HOT_ARTICLE_COLLECTION_WEIGHT = 8;
}
