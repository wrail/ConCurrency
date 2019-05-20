package com.wrial.main.example.singleton;

import com.wrial.main.annotations.NotThreadSafe;

/**
 * 饿汉模式,在第一次使用时进行创建，是不安全的
 * */
@NotThreadSafe
public class SingletonExample1 {

    private SingletonExample1() {
     //一些运算和逻辑操作
    }

    private static SingletonExample1 instance = null;

    //工厂
    public static SingletonExample1 getInstance() {
    //在多线程下不是线程安全的，因为可能同时很多线程在进行判空
        if (instance == null) {

            instance = new SingletonExample1();
        }
        return instance;

    }
}
