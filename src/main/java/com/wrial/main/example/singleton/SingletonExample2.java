package com.wrial.main.example.singleton;

import com.wrial.main.annotations.NotRecommend;
import com.wrial.main.annotations.NotThreadSafe;
import com.wrial.main.annotations.ThreadSafe;

/**
 * 对饿汉模式的改进，使得它变为安全的
 * */
@ThreadSafe
@NotRecommend
public class SingletonExample2 {

    private SingletonExample2() {
     //一些运算和逻辑操作
    }

    private static SingletonExample2 instance = null;

    //工厂，加入synchronized会变安全，只允许单线程，会增大服务器开销
    public synchronized static SingletonExample2 getInstance() {

        if (instance == null) {
            instance = new SingletonExample2();
        }
        return instance;

    }
}
