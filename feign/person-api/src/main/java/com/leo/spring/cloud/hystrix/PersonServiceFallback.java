package com.leo.spring.cloud.hystrix;

import com.leo.spring.cloud.domain.Person;
import com.leo.spring.cloud.service.PersonService;

import java.util.Collection;
import java.util.Collections;

public class PersonServiceFallback implements PersonService {
    @Override
    public boolean save(Person person) {
        return false;
    }

    @Override
    public Collection<Person> findAll() {
        return Collections.emptyList();
    }
}
