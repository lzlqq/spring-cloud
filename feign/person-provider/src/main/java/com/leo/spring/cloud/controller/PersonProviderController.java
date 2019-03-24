package com.leo.spring.cloud.controller;

import com.leo.spring.cloud.domain.Person;
import com.leo.spring.cloud.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 提供者接口（可选实现{@link PersonService}）
 */
@RestController
public class PersonProviderController {

    private Map<Long, Person> persons=new ConcurrentHashMap<>();

    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person){
        return persons.put(person.getId(),person)==null;
    }

    @GetMapping(value = "/person/find/all")
    public Collection<Person> findAll(){
        return persons.values();
    }
}
