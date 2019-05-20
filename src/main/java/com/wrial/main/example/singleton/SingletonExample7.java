package com.wrial.main.example.singleton;


import com.wrial.main.annotations.Recommend;
import com.wrial.main.annotations.ThreadSafe;

/**
 * 枚举模式，最安全的并且不会造成资源浪费
 *
 * */

@ThreadSafe
@Recommend
public class SingletonExample7 {
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }


    private enum Singleton {
        INSTANCE;
        //JVM保证这个方法绝对只调用一次
        private SingletonExample7 singleton;

        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {

            return singleton;
        }

    }


}
