package com.wrial.main.example.commonUnsafe;

import com.wrial.main.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 对SimpleDateFormat进行改进
 * 引用Joda下的DateTimeFormat，是线程安全的
 * 并且通过次数打印发现次数和格式都是符合要求的
 *
 * */
@Slf4j
@ThreadSafe
public class DateFormatExample3 {

    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    //joda的DateTimeFormat是安全的
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");
    private static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            //送给函数来打印每条记录和标号
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

    }

    private static void update(int count) {
        //生成的是DateTime对象调用toDate转换为日期字符串
//        int andIncrement = integer.getAndIncrement();
        int addIncrement = integer.incrementAndGet();
        log.info("{}——{}——{}", count, DateTime.parse("20190517", dateTimeFormatter).toDate(),addIncrement);
    }
}
