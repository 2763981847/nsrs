package com.group13.nsrs.constant;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
public class UserConstants {

    public static final String SERVER_NAME = "user";
    //token过期时间（1天）
    public static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
    //token签名密钥
    public static final String TOKEN_SIGN_KEY = "nsrs";

    public static final String USER_CODE_KEY = ProjectConstants.PROJECT_NAME + ":" + SERVER_NAME + ":code:";
    public static final String DEFAULT_PASSWORD = "123456";
}
