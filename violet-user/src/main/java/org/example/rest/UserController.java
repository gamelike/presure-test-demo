package org.example.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.AssetType;
import org.example.domain.UserService;
import org.example.domain.model.UserEntity;
import org.example.exception.model.ResponseResult;
import org.example.rest.model.user.UserVo;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name = "user-rest", description = "用户rest接口")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @Tag(name = "register", description = "register user")
    @PostMapping("register")
    public ResponseResult<UserVo> register(@RequestBody UserEntity user) {
        UserVo register = userService.registerUser(user);
        return ResponseResult.success(register);
    }

    @Tag(name = "login", description = "user login rest api")
    @GetMapping("login/{account}/{password}")
    public ResponseResult<UserVo> login(@PathVariable String account, @PathVariable String password,
                                        @RequestParam AssetType assetType) {
        return ResponseResult.success(userService.login(account, password, assetType));
    }

    @Tag(name = "active user", description = "active user by user id")
    @GetMapping("active/{userId}")
    public ResponseResult<Boolean> activeUser(@PathVariable long userId) {
        return ResponseResult.success(userService.activeUser(userId));
    }

    @Tag(name = "logout", description = "user logout")
    @GetMapping("logout/{userId}")
    public ResponseResult<Boolean> logout(@PathVariable long userId, @RequestParam AssetType assetType) {
        return ResponseResult.success(userService.logout(userId, assetType));
    }

}
