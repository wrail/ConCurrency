package com.wrial.main.example.aqs;

/*
 * 使用FutureTask
 *
 * */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
/*
        new Thread(new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("Do Something in Callable!");
                Thread.sleep(5000);
                return "All Things Done";
            }
        })).start();

 */
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("Do Something in Callable!");
                Thread.sleep(5000);
                return "All Things Done";
            }
        });

/*
        FutureTask futureTask1 = new FutureTask(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        },"Sssssss");
*/

        new Thread(futureTask).start();
        log.info("main is doing some things");


        String result = (String) futureTask.get();

        log.info("{}", result);
        Thread.sleep(1000);
        log.info("main is done");

    }

}
