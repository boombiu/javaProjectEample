package com.zjw.controller;

import com.zjw.model.User;
import com.zjw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @GetMapping("/login")
    public String login(@RequestBody User user){
        return userService.login(user);

    }


    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }
}
