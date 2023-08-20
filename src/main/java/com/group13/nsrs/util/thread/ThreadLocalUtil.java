package com.group13.nsrs.util.thread;


import com.group13.nsrs.model.entity.User;

/**
 * @author Fu Qiujie
 * @since 2023/7/28
 */
public class ThreadLocalUtil {
    private static final ThreadLocal<User> APP_USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUser(User user) {
        APP_USER_THREAD_LOCAL.set(user);
    }

    public static User getUser() {
        return APP_USER_THREAD_LOCAL.get();
    }

    public static void clear() {
        APP_USER_THREAD_LOCAL.remove();
    }
}
