package com.zjw.controller;

import com.zjw.constants.RespResult;
import com.zjw.model.LoginBody;
import com.zjw.model.User;
import com.zjw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjw
 * @since 2023-02-21
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;
    @PostMapping("/login")
    public RespResult login(@RequestBody LoginBody loginBody){
        return userService.login(loginBody);
    }


    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }
}
