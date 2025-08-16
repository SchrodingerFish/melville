package com.cn.melville.controller;

import com.cn.melville.common.Result;
import com.cn.melville.model.User;
import com.cn.melville.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qiuci
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public Result<User> getUserById(@PathVariable Integer id) {
        return Result.success(userService.findById(id));
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<List<User>> getAllUsers() {
        return Result.success(userService.findAll());
    }

    @PostMapping("/create")
    @ResponseBody
    public Result<Integer> createUser(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Result<Integer> updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id); // 确保 ID 一致
        return Result.success(userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result<Integer> deleteUser(@PathVariable Integer id) {
        return Result.success(userService.deleteById(id));
    }

}
