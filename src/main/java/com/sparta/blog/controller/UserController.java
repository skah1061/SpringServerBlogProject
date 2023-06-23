package com.sparta.blog.controller;

import com.sparta.blog.dto.UserRequestDto;
import com.sparta.blog.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public String signupUser(@RequestBody UserRequestDto userRequestDto){
        String respone = userService.signupUser(userRequestDto);
        return respone;
    }
    @PostMapping("/auth/login")
    public String loginUser(@RequestBody UserRequestDto userRequestDto){
        return userService.loginUser(userRequestDto);
    }
}
