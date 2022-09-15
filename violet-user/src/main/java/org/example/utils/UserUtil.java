package org.example.utils;

import org.example.infrastructure.model.po.User;
import org.springframework.lang.NonNull;

import javax.annotation.Nullable;

/**
 * store user entity to context in a request.
 *
 * @author violet
 * @since 2022/8/31
 */
public class UserUtil {

    private static final ThreadLocal<User> local = new ThreadLocal<>();

    @Nullable
    public static User get() {
        return local.get();
    }

    public static void set(@NonNull User user) {
        local.set(user);
    }

    public static void clear() {
        local.remove();
    }

}
