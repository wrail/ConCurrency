package com.wrial.main.example.singleton;

import com.wrial.main.annotations.NotRecommend;
import com.wrial.main.annotations.NotThreadSafe;
import com.wrial.main.annotations.ThreadSafe;

/**
 * 懒汉模式，是安全的，因为它
 */

@NotThreadSafe
@NotRecommend
public class SingletonExample3 {


    private SingletonExample3() {
        //一些运算和逻辑操作
    }

    //单例对象
    private static SingletonExample3 instance = new SingletonExample3();
    //静态的工厂方法
    public static SingletonExample3 getInstance() {
        return instance;

    }
}
