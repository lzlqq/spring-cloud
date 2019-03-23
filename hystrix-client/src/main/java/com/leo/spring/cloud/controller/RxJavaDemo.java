package com.leo.spring.cloud.controller;

import rx.Observer;
import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

public class RxJavaDemo {
    public static void main(String[] args) {
        Random random = new Random();

        Single.just("Hello world")// just 发布数据
                .subscribeOn(Schedulers.immediate())// 订阅的线程池 immediate=Thread.currentThread()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {// 正常流程
                        System.out.println("执行结束");
                    }

                    @Override
                    public void onError(Throwable throwable) {// 异常流程
                        System.out.println("熔断保护");
                    }

                    @Override
                    public void onNext(String s) {//消费数据,s="Hello world"
                        //超时触发容错
                        int value = random.nextInt(200);

                        if (value > 100) {
                            throw new RuntimeException("Timeout!");
                        }
                        // 还能打印，和Hystrix不太一样，Hystrix放在Thread#sleep之后不打印
                        System.out.println("helloWorld() costs " + value + " ms.");
                    }
                });
    }
}
