package org.example.config.interception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.UnAuthorizedException;
import org.example.infrastructure.model.constant.UserStatus;
import org.example.infrastructure.model.po.User;
import org.example.utils.UserUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * user bill.
 * first interceptor.
 */
@RequiredArgsConstructor
@Slf4j
public class GlobalInterception implements HandlerInterceptor {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String token = request.getHeader("token");
        User user = (User) redisTemplate.opsForValue().get(token);
        if (Objects.isNull(user)) {
            throw new UnAuthorizedException("未授权认证!");
        }
        if (user.getUserStatus().equals(UserStatus.unactivated)) {
            throw new UnAuthorizedException("未激活账号!");
        }
        UserUtil.set(user);
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
        UserUtil.clear();
    }

}
