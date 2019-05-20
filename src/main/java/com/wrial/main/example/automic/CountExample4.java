package com.wrial.main.example.automic;


import com.wrial.main.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 并发模拟测试
 * 原子性Atomci包   AtomicReference
 */

@Slf4j
@ThreadSafe
public class CountExample4 {
    public static AtomicReference<Integer> reference = new AtomicReference<>(0);

    public static void main(String[] args) {

        reference.compareAndSet(0, 1);
        reference.compareAndSet(0, 2);
        reference.compareAndSet(1, 3);
        reference.compareAndSet(2, 4);
        log.info("reference:{}",reference.get());
    }
}
