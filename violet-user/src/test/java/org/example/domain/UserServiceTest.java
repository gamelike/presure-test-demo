package org.example.domain;

import org.example.base.annotation.BaseTest;
import org.example.constant.AssetType;
import org.example.domain.model.UserEntity;
import org.example.exception.UnAuthorizedException;
import org.example.rest.model.user.UserRequest;
import org.example.rest.model.user.UserVo;
import org.example.utils.RedisKeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@BaseTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    final UserEntity user = new UserEntity("test", "test", "community-test", "123456",
            "test", "aa@aa.com", "1");

    @Test
    void when_register_user_return_userEntity() {
        UserVo userVo = userService.registerUser(user);
        assertThat(userVo).isNotNull();
        assertThat(userVo.account()).isEqualTo(user.account());
        assertThat(userVo.firstName()).isEqualTo(user.firstName());
        assertThat(userVo.lastName()).isEqualTo(user.lastName());
        assertThat(userVo.username()).isEqualTo(user.username());
        assertThat(userVo.email()).isEqualTo(user.email());
    }

    @Test
    void when_login_user_return_userEntity() {
        UserVo userVo = userService.registerUser(user);
        assertThat(userVo).isNotNull();
        try {
            UserVo login = userService.login(user.account(), user.password(), AssetType.COMPUTE);
            assertThat(login).isNull();
        } catch (UnAuthorizedException e) {
            assertThat(e).isNotNull();
        }
        boolean active = userService.activeUser(userVo.id());
        assertThat(active).isTrue();
        UserVo login = userService.login(user.account(), user.password(), AssetType.COMPUTE);
        assertThat(redisTemplate.opsForValue().get(RedisKeyUtil.getTokenKey(user.account(), AssetType.COMPUTE))).isNotNull();
        assertThat(login).isNotNull();
    }


    @Test
    void when_logout_user_return_token_remove() {
        UserVo userVo = userService.registerUser(user);
        assertThat(userVo).isNotNull();
        boolean active = userService.activeUser(userVo.id());
        assertThat(active).isTrue();
        UserVo login = userService.login(user.account(), user.password(), AssetType.COMPUTE);
        assertThat(redisTemplate.opsForValue().get(RedisKeyUtil.getTokenKey(user.account(), AssetType.COMPUTE))).isNotNull();
        assertThat(login).isNotNull();
        boolean logout = userService.logout(userVo.id(), AssetType.COMPUTE);
        assertThat(logout).isTrue();
        assertThat(redisTemplate.opsForValue().get(RedisKeyUtil.getTokenKey(user.account(), AssetType.COMPUTE))).isNull();
    }

    @Test
    void find_user_page() {
        UserVo userVo = userService.registerUser(user);
        assertThat(userVo).isNotNull();
        Page<UserVo> userList = userService.findUserList(new UserRequest(null, 0, 1));
        assertThat(userList).isNotNull();
        assertThat(userList.getTotalElements()).isEqualTo(1L);
        assertThat(userList.getContent().get(0)).isEqualTo(userVo);
    }

}