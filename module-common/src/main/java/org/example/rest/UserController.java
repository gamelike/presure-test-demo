package org.example.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.UserService;
import org.example.domain.model.UserEntity;
import org.example.exception.model.ResponseResult;
import org.example.infrastructure.model.po.User;
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

    @GetMapping("{username}")
    public ResponseResult<List<User>> findUserByName(@PathVariable String username) {
        return ResponseResult.success(userService.findUserByName(username));
    }

    @GetMapping("findAll")
    public ResponseResult<List<User>> findAll() {
        return ResponseResult.success(userService.findAll());
    }

}
