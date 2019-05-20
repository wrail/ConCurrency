package com.wrial.main.example.commonUnsafe;

import com.wrial.main.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/*
 * ArrayList不安全
 * HashSet也是不安全的，注意HashSet每次传值的时候不能传同样的值，不然容量始终为1
 * HashMap也是不安全的
 * */

@Slf4j
@NotThreadSafe
public class ArrayListExample {
    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    //    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static HashSet hashSet = new HashSet();


    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {

                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("{}", hashSet.size());

    }

    private static void update(int i) {
        //HashSet不能每次加同一个值否则就用于size为1
        hashSet.add(i);
//        System.out.println(hashSet.size());

    }

}
