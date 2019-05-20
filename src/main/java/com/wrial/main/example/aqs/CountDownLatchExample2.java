package com.wrial.main.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * 规定任务事件，如果没按时完成就不管了
 * */
@Slf4j
public class CountDownLatchExample2 {

    private static final int conutThrad = 200;

    public static void test(int count) throws Exception{
        Thread.sleep(1000);
        log.info("{}", count);
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exc = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(conutThrad);
        for (int i = 0; i < conutThrad; i++) {
            final int count = i;
            exc.execute(() -> {
                try {
                    test(count);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(500,TimeUnit.MILLISECONDS);
        log.info("Finish");
        exc.shutdown();


    }

}
