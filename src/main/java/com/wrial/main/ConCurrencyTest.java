package com.wrial.main;


import com.wrial.main.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;
/**
 * 并发模拟测试
 *
 * */


@Slf4j
@NotThreadSafe
public class ConCurrencyTest {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;


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
        count++;
    }
}
