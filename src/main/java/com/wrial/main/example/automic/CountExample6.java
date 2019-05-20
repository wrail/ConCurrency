package com.wrial.main.example.automic;


import com.wrial.main.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 并发模拟测试
 * 原子性Atomic包   AtomicBoolean可以用来做那些只做一次的事情
 */

@Slf4j
@ThreadSafe
public class CountExample6 {

    static AtomicBoolean atomicBoolean = new AtomicBoolean();
    public static int clientTotal = 5000;

    public static int threadTotal = 200;


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
                    test();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("atomicBoolean:{}", atomicBoolean.get());

    }

    public static void test() {
//        log.info("Before:{}",atomicBoolean.get());

        if (atomicBoolean.compareAndSet(false, true)) {

            log.info("Success:{}", atomicBoolean.get());
        }
    }

}