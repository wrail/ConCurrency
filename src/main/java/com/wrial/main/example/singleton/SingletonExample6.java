package com.wrial.main.example.singleton;

import com.wrial.main.annotations.NotRecommend;
import com.wrial.main.annotations.ThreadSafe;

/**
 * 饿汉模式的另一种实现（静态代码块）
 */

@ThreadSafe
@NotRecommend
public class SingletonExample6 {


    private SingletonExample6() {
        //一些运算和逻辑操作
    }
    //单例对象,一定要放在static前，不然就空指针了
    private static SingletonExample6 instance = null;
    static {

        instance = new SingletonExample6();
    }
    //静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(instance.hashCode());
    }
}
