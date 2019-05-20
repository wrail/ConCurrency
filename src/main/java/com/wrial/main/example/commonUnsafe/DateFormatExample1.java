package com.wrial.main.example.commonUnsafe;

import com.wrial.main.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
* SimpleDateFormat不是一个线程安全的类，在并发环境下会引发异常
*
* */
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

   private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMdd");

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

    }

    private static void update() {
        try {
            simpleDateFormat.parse("20190517");
        } catch (ParseException e) {
           log.info("parse error!");
        }
    }
}
