package com.wrial.main.example.automic;


import com.wrial.main.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 并发模拟测试
 * 原子性Atomci包 LongAddr  JDK8加的   相比于AutomicLong 优点是在高并发时将单点转化为多点，线程竞争低比较适合使用AutomicLong
 */

@Slf4j
@ThreadSafe
public class CountExample3 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    //    public static int count = 0;
    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {

                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("cout:{}", count);

    }

    private static void add() {
//        count++;
        count.increment();

    }
}
