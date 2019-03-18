package com.leo.spring.cloud.web.controller;

import com.leo.spring.cloud.domain.User;
import com.leo.spring.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
public class UserProviderRestApiController {

    @Autowired
    private UserService userService;

    /**
     * @param user User
     * @return 如果保存成功的话，返回{@link User},否则返回<code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {
        if (userService.save(user)) {
            return user;
        } else {
            return null;
        }
    }

    @GetMapping("/user/list")
    public Collection<User> list() {
        return userService.findAll();
    }
}
