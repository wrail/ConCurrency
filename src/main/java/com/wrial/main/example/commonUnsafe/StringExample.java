package com.wrial.main.example.commonUnsafe;


import com.wrial.main.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试StringBuilder是线程不安全的类
 * StringBuffer是线程安全的
 */

@Slf4j
@NotThreadSafe
public class StringExample {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    //StringBuilder是不安全的,高效，在一个方法内局部变量使用的话是堆栈封闭的，不会造成线程不安全（只有单个线程能操作）
//    public static StringBuilder stringBuilder = new StringBuilder();

    //加了Synchronzied关键字，在多线程并发情况下
    public static StringBuffer stringBuilder = new StringBuffer();

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
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("length:{}", stringBuilder.length());

    }

    private static void update() {
        stringBuilder.append("1");
    }
}
