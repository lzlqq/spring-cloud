package com.leo.spring.cloud.controller;

import com.leo.spring.cloud.domain.User;
import com.leo.spring.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserRestApiController {
    @Autowired
    private UserService userService;

    /**
     * @param name
     * @return 如果保存成功的话，返回{@link User},否则返回<code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestParam String name) {
        User user = new User();
        user.setName(name);
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
