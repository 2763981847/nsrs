package com.group13.nsrs.interceptor;

import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.util.JwtHelper;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        User user = JwtHelper.getUser(token);
        if (user != null) {
            ThreadLocalUtil.setUser(user);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.clear();
    }
}
