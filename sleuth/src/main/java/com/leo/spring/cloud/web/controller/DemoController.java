package com.leo.spring.cloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    @Autowired
    RestTemplate restTemplate;

    protected final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("")
    public String index() {
        String returnValue = "Hello World";
        logger.info("{} index() : {}", getClass().getSimpleName(), returnValue);
        return returnValue;
    }

    @GetMapping("/to/zuul/person-client/person/find/all")
    public Object toZuul() {
        logger.info("toZuul");
        String url = "http://zuul/person-client/person/find/all";
        return restTemplate.getForObject(url,Object.class);
    }
}
