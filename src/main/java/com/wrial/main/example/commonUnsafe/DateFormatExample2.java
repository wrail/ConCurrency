package com.wrial.main.example.commonUnsafe;

import com.wrial.main.annotations.NotThreadSafe;
import com.wrial.main.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
* 对SimpleDateFormat进行改进，每次new一个SimpleDateFormat实例
* 不会出现问题
*
* */
@Slf4j
@ThreadSafe
public class DateFormatExample2 {
    public static int clientTotal = 5000;
    public static int threadTotal = 200;

//   private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

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
            //每次new一个实例
            new SimpleDateFormat("yyyyMMdd").parse("20190117");
        } catch (ParseException e) {
           log.info("parse error!");
        }
    }
}
