package com.wrial.main.example.aqs;

/*
 * 使用CycliBarrier
 * */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

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
            Thread.sleep(100);
            final int num = i;
            exe.execute(() -> {
                try {
                    runAndWait(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        exe.shutdown();
    }

}
