package com.wrial.main.example.myLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/*
 * 实现可重入锁
 *
 * */
public class MyLock2 implements Lock {

    private boolean isLock = false;
    private Thread lockBy = null;
    private int lockCount = 0;

    @Override
    public void lock() {

        Thread currentThread = Thread.currentThread();
        while (isLock && lockBy != currentThread) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;
        lockBy = currentThread;
        lockCount++;
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

    //必须要加synchronized
    @Override
    public synchronized void unlock() {
        if (lockBy == Thread.currentThread()) {
            lockCount--;
            if (lockCount == 0) {
                notify();
                isLock = false;
                lockBy = null;
            }
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
