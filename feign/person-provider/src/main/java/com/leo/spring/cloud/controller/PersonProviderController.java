package com.leo.spring.cloud.controller;

import com.leo.spring.cloud.domain.Person;
import com.leo.spring.cloud.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 提供者接口（可选实现{@link PersonService}）
 */
@RestController
public class PersonProviderController {

    private Map<Long, Person> persons = new ConcurrentHashMap<>();
    private final Random random = new Random();

    @PostMapping(value = "/person/save")
    public boolean save(@RequestBody Person person) {
        return persons.put(person.getId(), person) == null;
    }

    @GetMapping(value = "/person/find/all")
    @HystrixCommand(fallbackMethod = "fallbackForFindAllPersons", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "100")
    })
    public Collection<Person> findAll() throws InterruptedException {
        //超时触发容错
        int value =random.nextInt(200);
        Thread.sleep(value);
        System.out.println("findAll() costs "+value+" ms.");
        return persons.values();
    }

    /**
     * {@link #findAll()} Fallback 方法
     * @return 返回空集合
     */
    public  Collection<Person> fallbackForFindAllPersons(){
        System.out.println("fallbackForFindAllPersons() is invoked");
        return Collections.emptyList();
    }
}
