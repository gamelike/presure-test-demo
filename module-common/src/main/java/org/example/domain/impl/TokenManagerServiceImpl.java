package org.example.domain.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.AssetType;
import org.example.domain.TokenManagerService;
import org.example.domain.model.TokenEntity;
import org.example.infrastructure.model.po.User;
import org.example.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenManagerServiceImpl implements TokenManagerService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final long EXPIRE_TIME = 30;

    @Override
    public TokenEntity findToken(String account, AssetType assetType) {
        String token = (String) redisTemplate.opsForValue().get(RedisKeyUtil.getTokenKey(account, assetType));
        return new TokenEntity(account, token);
    }

    @Override
    public TokenEntity createToken(String account, AssetType assetType, User user) {
        Objects.requireNonNull(account);
        redisTemplate.opsForValue().set(RedisKeyUtil.getTokenKey(account, assetType), user, EXPIRE_TIME, TimeUnit.DAYS);
        return new TokenEntity(account, RedisKeyUtil.getTokenKey(account, assetType));
    }

    @Override
    public boolean subscribeToken(String account, AssetType assetType) {
        Objects.requireNonNull(account);
        Boolean expire = redisTemplate.expire(RedisKeyUtil.getTokenKey(account, assetType), EXPIRE_TIME, TimeUnit.DAYS);
        Objects.requireNonNull(expire);
        return expire;
    }

    @Override
    public boolean deleteToken(String account, AssetType assetType) {
        Objects.requireNonNull(account);
        Boolean delete = redisTemplate.delete(RedisKeyUtil.getTokenKey(account, assetType));
        Objects.requireNonNull(delete);
        return delete;
    }

    /////////////////////
    // private method ///
    /////////////////////

}
