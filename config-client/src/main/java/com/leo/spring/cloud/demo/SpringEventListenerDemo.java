package com.leo.spring.cloud.demo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义监听器
 */
public class SpringEventListenerDemo {
    public static void main(String[] args) {
        //Annotation 驱动的Spring上下文
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        //注册监听器
        context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            /**
             * 监听器得到事件
             * @param applicationEvent
             */
            @Override
            public void onApplicationEvent(MyApplicationEvent applicationEvent) {
                System.out.println(applicationEvent.getSource());
            }
        });
        //刷新上下文
        context.refresh();
        //发布事件
        context.publishEvent(new MyApplicationEvent("hello"));
        context.publishEvent(new MyApplicationEvent(666));
    }
    private static class MyApplicationEvent extends ApplicationEvent{

        public MyApplicationEvent(Object source) {
            super(source);
        }
    }
}
