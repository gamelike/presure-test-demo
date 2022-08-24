package org.example.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.UserService;
import org.example.domain.model.UserEntity;
import org.example.exception.model.ResponseResult;
import org.example.infrastructure.model.po.User;
import org.example.rest.model.user.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseResult<User> register(@RequestBody UserEntity user) {
        User register = userService.registerUser(user);
        return ResponseResult.success(register);
    }

    @GetMapping("login/{account}/{password}")
    public ResponseResult<User> login(@PathVariable String account,@PathVariable String password) {
        return ResponseResult.success(userService.login(account, password));
    }

    @GetMapping("list")
    public ResponseResult<Page<User>> getUserList(@RequestParam UserRequest request) {
        return ResponseResult.success();
    }
}
