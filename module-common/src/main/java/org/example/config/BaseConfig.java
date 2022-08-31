package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.interception.GlobalInterception;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author violet
 * @since 2022/8/31
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class BaseConfig implements WebMvcConfigurer {

    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        new InterceptorRegistry().addInterceptor(new GlobalInterception(redisTemplate))
                .excludePathPatterns(List.of("/community/user/**"));
    }

}
