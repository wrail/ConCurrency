package com.wrial.main.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/*
 * 信号量的使用
 * */
@Slf4j
public class SemaphoreExample1 {

    private static final int conutThrad = 30;

    public static void test(int count) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}", count);
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exc = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);//定义资源运行最大并发量为20
        for (int i = 0; i < conutThrad; i++) {
            final int count = i;
            exc.execute(() -> {
                try {
                    semaphore.acquire();//获得一个许可,也可以获取多个许可
                    test(count);
                    semaphore.release();//释放一个许可，也可以释放多个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exc.shutdown();


    }

}
