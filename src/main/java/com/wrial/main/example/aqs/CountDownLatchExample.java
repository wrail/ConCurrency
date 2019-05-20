package com.wrial.main.example.aqs;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CountDownLatchExample {

    private static final int count = 200;

    public static void test(int threadNum) throws InterruptedException {

        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);


    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exc = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            final int num = i;
            exc.execute(() -> {
                try {
                    test(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //保证肯定会执行，在其他情况可以在某些条件下才会countdown
                    countDownLatch.countDown();
                }
            });
        }
        //可以保证前面的执行完，对count=0进行校验
        countDownLatch.await();
        log.info("finish");
        exc.shutdown();

    }

}
