package com.leo.spring.cloud.service;

import com.leo.spring.cloud.domain.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

@FeignClient(value = "person-service")//服务提供方应用的名称
public interface PersonService {
    /**
     * 保存
     *
     * @param person {@link Person}
     * @return
     */
    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person);

    @GetMapping(value = "/person/find/all")
    public Collection<Person> findAll();
}
