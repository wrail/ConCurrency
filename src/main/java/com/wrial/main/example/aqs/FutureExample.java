package com.wrial.main.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {


    static class MyCallable implements Callable {

        @Override
        public String call() throws Exception {
            log.info("Do Some Things in callable!");
            Thread.sleep(4000);
            return "Callable Things Done";
        }
    }

    public static void main(String[] args) throws Exception {

        ExecutorService exe = Executors.newCachedThreadPool();
        Future future = exe.submit(new MyCallable());
        log.info("do some thing in main");
        Thread.sleep(1000);
        //如果获取不到就一直阻塞
        String mes = (String) future.get();
        log.info("{}", mes);
        log.info("main done");
        exe.shutdown();


    }

}
