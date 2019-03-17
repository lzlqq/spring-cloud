package com.leo.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class EchoController {
    @Value("${my.name}")
    private String myName;

    @GetMapping("/my-name")
    public String getName() {
        return myName;
    }
}
