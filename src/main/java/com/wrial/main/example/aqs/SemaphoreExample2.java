package com.wrial.main.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * 信号量的使用
 * */
@Slf4j
public class SemaphoreExample2{

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
                    //能获取到信号量的就可以使用，获取不到就舍弃
//                    if (semaphore.tryAcquire()) {
//                        test(count);
//                        semaphore.release();//释放一个许可，也可以释放多个许可
//                    }
                    //可以传入参数，设置信号量的超时时间，针对于多线程
//                    if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {
//                        test(count);
//                        semaphore.release();
//                    }
                    if (semaphore.tryAcquire(3,5, TimeUnit.SECONDS)) {
                        test(count);
                        semaphore.release(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exc.shutdown();


    }

}
