package com.wrial.main.example.myLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 定义一个自己的锁
 */
public class Mylock1 implements Lock {

    private boolean isLock = false;

    //为了处理多线程，加上Synchronized
    @Override
    public synchronized void lock() {

        //不能用if，使用while自旋
        while (isLock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {

        isLock = false;
        notify();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
