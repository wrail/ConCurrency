package com.wrial.main.example.automic;


import com.wrial.main.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 并发模拟测试
 * 原子性Atomci包   AtomicReference
 */

@Slf4j
@ThreadSafe
public class CountExample5 {

    static AtomicIntegerFieldUpdater<CountExample5> updater
            = AtomicIntegerFieldUpdater.newUpdater(CountExample5.class, "count");

    @Getter
    volatile int count = 1;//必须是volatile修饰的，并且不能是static的   为什么换位Integer就报错

    public static void main(String[] args) {
        CountExample5 example5 = new CountExample5();
        if (updater.compareAndSet(example5,1,2)){
            log.info("countUpdateSuccess1:{}",example5.getCount());
        }
        if (updater.compareAndSet(example5,2,3)){
            log.info("countUpdateSuccess2:{}",example5.getCount());
        }


    }
}