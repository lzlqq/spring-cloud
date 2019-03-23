package com.leo.spring.cloud.controller;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {
        Random random=new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String>  future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //超时触发容错
                int value =random.nextInt(200);

                Thread.sleep(value);
                // 还能打印，和Hystrix不太一样，Hystrix放在Thread#sleep之后不打印
                System.out.println("helloWorld() costs "+value+" ms.");
                return "Hello World";
            }
        });

        try {
            future.get(100,TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();////////
            //超时流程
            System.out.println("超时保护！");
        }

        executorService.shutdown();
    }
}
