package com.group13.nsrs.util;


import com.alibaba.fastjson.JSON;
import com.group13.nsrs.constant.UserConstants;
import com.group13.nsrs.model.entity.User;
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
     * @param user 用户id
     * @return token
     */

    public static String createToken(User user) {
        return Jwts.builder()
                .setSubject("NSRS-USER")
                .setExpiration(new Date(System.currentTimeMillis() + UserConstants.TOKEN_EXPIRATION))
                .claim("user", JSON.toJSONString(user))
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
    public static User getUser(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(UserConstants.TOKEN_SIGN_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String userJsonString = claims.get("user", String.class);
        return JSON.parseObject(userJsonString, User.class);
    }

}
