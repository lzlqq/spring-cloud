package com.leo.spring.cloud.service;

import com.leo.spring.cloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
@Service
public class UserServiceProxy implements UserService {

    private static final String PROVIDER_SERVER_URL_PREFIX = "http://eureka-client-provider";


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean save(User user) {
        //调用的时候要看清楚参数时候合法
        return restTemplate.postForObject(PROVIDER_SERVER_URL_PREFIX + "/user/save", user, User.class) != null;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/list", Collection.class);
    }
}
