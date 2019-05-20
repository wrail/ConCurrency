package com.wrial.main.example.singleton;

import com.wrial.main.annotations.NotThreadSafe;
import com.wrial.main.annotations.ThreadSafe;

/**
 * 懒汉模式-》双重同步锁单例模式
 * volitile+doubleCheck->禁止指令重排
 */

@ThreadSafe
public class SingletonExample5 {


    private SingletonExample5() {
        //一些运算和逻辑操作
    }
    /**
     * 1.memory = allacate()分配对象的内存空间
     * 2.ctorInstance（） 初始化对象
     * 3.instance = memory 设置为instance执行刚分配的内存
     */
    /**
     * JVM和cpu优化，发生了指令重排导致了上边的2和三发生了交换
     * 虽然是偶然现象，但是还是一种异常
     * 比如A先进入了CPU使用资源，执行到了重排后的指令的第二步（也就是原本的第三步）
     * 然后在还没有进行初始化时，B来了，进行判断，发现有instance的实例，就直接返回
     * 这样就造成了线程不按全
     * */

    /**
     * 为了解决这一问题，防止指令重排，就采用了volatile限制指令重排
     * 这样就变为安全的了
     */
    //单例对象  volitile+doubleCheck->禁止指令重排
    private volatile static SingletonExample5 instance = null;

    //静态的工厂方法
    public static SingletonExample5 getInstance() {
        //双重检查，第一层检查是当前占用cpu的线程，第二层检查的是其余线程
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    return new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
