package com.js.springbootstart.controller;

import com.js.springbootstart.pojo.ResponseMessage;
import com.js.springbootstart.pojo.User;
import com.js.springbootstart.pojo.dto.UserDto;
import com.js.springbootstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController //接口方法返回对象，转换成json文本
@RequestMapping("/user") //localhost:8080/user/**
public class UserController {

    @Autowired
    private UserService userService;

    // 增加
    @PostMapping
    public ResponseMessage addUser(@Validated @RequestBody UserDto user) {
        System.out.println(user.toString());
        User newUser = userService.addUser(user);
        return ResponseMessage.success(newUser);
    }

    // 查询
    @GetMapping("/{userId}")
    public ResponseMessage getUserById(@PathVariable Integer userId) {
        User newUser = userService.getUserById(userId);
        return ResponseMessage.success(newUser);
    }


    //修改
    @PutMapping
    public ResponseMessage updateUser(@Validated @RequestBody UserDto user) {
        User newUser = userService.updateUser(user);
        return ResponseMessage.success(newUser);
    }

    //删除用户
    @DeleteMapping("/{userId}")
    public ResponseMessage deleteUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseMessage.success();
    }
}
