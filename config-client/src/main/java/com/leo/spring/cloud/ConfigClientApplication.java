package com.leo.spring.cloud;

import com.leo.spring.cloud.health.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class ConfigClientApplication {
    // 自动更新服务端配置信息实现：
    // --> 这种方式适合：开关、阈值、文档等，不适合复杂配置变更
    // 1.查看 refresh 接口 得知GenericPostableMvcEndpoint 依赖ContextRefresher和Environment
    // 2.查看beans接口得到以上两个依赖的Bean实例，通过构造器注入实现
    private final ContextRefresher contextRefresher;

    private final Environment environment;

    @Autowired
    public ConfigClientApplication(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }

    public static void main(String[] args) {
        //SpringApplication springApplication=new SpringApplication(ConfigClientApplication.class);
        //springApplication.setWebEnvironment(true);
        //springApplication.run(args);
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    // 自动更新服务端配置信息
    @Scheduled(fixedRate = 5 * 1000, initialDelay = 3 * 1000)
    public void autoRefresh() {
        Set<String> updatedPropertyNames = contextRefresher.refresh();

        updatedPropertyNames.forEach(propertyName -> System.out.printf("[Thread :%s] 当前配置已更新，具体项目 Key：%s ,Value: %s \n",
                Thread.currentThread().getName(),
                propertyName,
                environment.getProperty(propertyName)));

//        if (!updatedPropertyNames.isEmpty()) {
//            System.out.printf("[Thread :%s] 当前配置已更新，具体项目：%s \n",
//                    Thread.currentThread().getName(),
//                    updatedPropertyNames);
//        }
    }

    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class MyPropertySourceLoactor implements PropertySourceLocator {

        @Override
        public PropertySource<?> locate(Environment environment) {
            Map<String, Object> source = new HashMap<>();
            //source.put("server.port","9090");
            MapPropertySource propertySource = new MapPropertySource("my-property-source", source);
            return propertySource;
        }
    }

    @Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }

}
