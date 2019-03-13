package com.leo.spring.cloud.demo;

import java.util.Observable;
import java.util.Observer;

public class ObserverDemo {
    public static void main(String[] args) {
        Observable observable = new MyObservable();
        //增加订阅者
        observable.addObserver(new Observer() {
            public void update(Observable o, Object arg) {
                System.out.println(arg);
            }
        });
        //需要设置，才能看到打印值
        ((MyObservable) observable).setChanged();
        //发布者通知，订阅者是被动感知（推模式）
        observable.notifyObservers("hello");
    }

    public static class MyObservable extends Observable {
        public void setChanged() {
            super.setChanged();
        }
    }
}
