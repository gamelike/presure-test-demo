package org.example.domain;

import org.assertj.core.api.Assertions;
import org.example.base.annotation.BaseTest;
import org.example.constant.AssetType;
import org.example.domain.model.TokenEntity;
import org.example.infrastructure.model.po.User;
import org.example.utils.RedisKeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@BaseTest
class TokenManagerServiceTest {

    @Autowired
    TokenManagerService tokenManagerService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    final String account = "account";
    final AssetType assetType = AssetType.COMPUTE;

    final User user = new User().setAccount(account).setEmail("test@gmail.com").setPassword("123456");

    @Test
    void store_token_to_redis_return_uuid() {
        TokenEntity token = tokenManagerService.createToken(account, assetType, user);
        Assertions.assertThat(token.token()).isEqualTo(RedisKeyUtil.getTokenKey(account, assetType));
        Assertions.assertThat(token.account()).isEqualTo(account);
        User target = (User) redisTemplate.opsForValue().get(token.token());
        // json 序列化会再次摘要密码
        user.setPassword(user.getPassword());
        Assertions.assertThat(user).isEqualTo(target);
    }

    @Test
    void find_token_by_redis_return_true() {
        TokenEntity token = tokenManagerService.createToken(account, assetType, user);
        Long expire = redisTemplate.getExpire(token.token(), TimeUnit.DAYS);
        Assertions.assertThat(expire).isEqualTo(29);
        TokenEntity findResult = tokenManagerService.findToken(account, assetType);
        Assertions.assertThat(findResult).isNotNull();
        Assertions.assertThat(findResult).isEqualTo(token);
    }


    @Test
    void find_token_by_redis_and_subscribe_expire_time() {
        TokenEntity token = tokenManagerService.createToken(account, assetType, user);
        Long expire = redisTemplate.getExpire(token.token(), TimeUnit.DAYS);
        Assertions.assertThat(expire).isEqualTo(29);
        Boolean time = redisTemplate.expire(token.token(), 10, TimeUnit.DAYS);
        Assertions.assertThat(time).isTrue();
        boolean flag = tokenManagerService.subscribeToken(account, assetType);
        Assertions.assertThat(flag).isTrue();
        Long result = redisTemplate.getExpire(token.token(), TimeUnit.DAYS);
        Assertions.assertThat(result).isEqualTo(29);
    }

    @Test
    void delete_redis_token_return_null() {
        TokenEntity token = tokenManagerService.createToken(account, assetType, user);
        boolean deleted = tokenManagerService.deleteToken(account, assetType);
        Assertions.assertThat(deleted).isTrue();
        User u = (User) redisTemplate.opsForValue().get(token.token());
        Assertions.assertThat(u).isNull();
    }
}