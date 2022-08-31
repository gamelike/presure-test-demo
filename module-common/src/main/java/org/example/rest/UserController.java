package org.example.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.AssetType;
import org.example.domain.UserService;
import org.example.domain.model.UserEntity;
import org.example.exception.model.ResponseResult;
import org.example.rest.model.user.UserRequest;
import org.example.rest.model.user.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractSet;

@RequestMapping("user")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseResult<UserVo> register(@RequestBody UserEntity user) {
        UserVo register = userService.registerUser(user);
        return ResponseResult.success(register);
    }

    @GetMapping("login/{account}/{password}")
    public ResponseResult<UserVo> login(@PathVariable String account, @PathVariable String password,
                                        @RequestParam AssetType assetType) {
        return ResponseResult.success(userService.login(account, password, assetType));
    }

    @GetMapping("active/{userId}")
    public ResponseResult<Boolean> activeUser(@PathVariable long userId) {
        return ResponseResult.success(userService.activeUser(userId));
    }

    @GetMapping("logout/{userId}")
    public ResponseResult<Boolean> logout(@PathVariable long userId, @RequestParam AssetType assetType) {
        return ResponseResult.success(userService.logout(userId, assetType));
    }

}
