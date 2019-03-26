package com.leo.spring.cloud.controller;

import com.leo.spring.cloud.domain.Person;
import com.leo.spring.cloud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PersonClientController {

    @Autowired
    PersonService personService;

    @PostMapping(value = "/person/save")

    public boolean save(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping(value = "/person/find/all")
    public Collection<Person> findAll() {

        return personService.findAll();
    }
}
