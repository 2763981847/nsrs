package com.group13.nsrs.util;


import com.group13.nsrs.constant.UserConstants;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author : Fu QiuJie
 * @date : 2022/10/9 20:37
 */
public class JwtHelper {

    /**
     * 根据参数设置token
     *
     * @param userId   用户id
     * @param userName 用户名
     * @return token
     */
    public static String createToken(Long userId, String userName) {
        return Jwts.builder()
                .setSubject("NSRS-USER")
                .setExpiration(new Date(System.currentTimeMillis() + UserConstants.TOKEN_EXPIRATION))
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, UserConstants.TOKEN_SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static String createToken(Long userId) {
        return Jwts.builder()
                .setSubject("NSRS-USER")
                .setExpiration(new Date(System.currentTimeMillis() + UserConstants.TOKEN_EXPIRATION))
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS512, UserConstants.TOKEN_SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 根据token获取到用户id
     *
     * @param token token
     * @return 用户id
     */
    public static Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(UserConstants.TOKEN_SIGN_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId.longValue();
    }

    /**
     * 根据token拿到用户名
     *
     * @param token token
     * @return 用户名
     */
    public static String getUserName(String token) {
        if (StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(UserConstants.TOKEN_SIGN_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userName");
    }

}
