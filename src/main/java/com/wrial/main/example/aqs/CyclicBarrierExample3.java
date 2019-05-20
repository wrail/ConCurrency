package com.wrial.main.example.aqs;

/*
 * 使用CyclicBarrier,并传入Runable
 * */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample3 {

    //传入的Runnable是在到达屏障后，第一个执行
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
       log.info("I Am The First Runnable Will Be Do ");
    });

    private static int threadCount = 20;

    public static void runAndWait(int num) throws Exception {
        Thread.sleep(100);
        log.info("{} is comming", num);
        cyclicBarrier.await();
        log.info("{} is going", num);

    }

    public static void main(String[] args) throws Exception {

        ExecutorService exe = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            Thread.sleep(1000);
            final int num = i;
            exe.execute(() -> {
                try {
                    runAndWait(num);
                } catch (Exception e) {

                }
            });
        }

        exe.shutdown();
    }

}
