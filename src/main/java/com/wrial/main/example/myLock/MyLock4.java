package com.wrial.main.example.myLock;

/*
 * 使用AQS来实现可重入锁
 * */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock4 implements Lock {


    private class Helper extends AbstractQueuedSynchronizer {


        //1.获取状态

        //2.拿到状态，CAS判断(双重检测)，并设置为独占锁

        //3.判断是不是当前线程，要是当前线程的话就可重入

        @Override
        protected boolean tryAcquire(int arg) {

            Thread t = Thread.currentThread();
            int state = getState();
            if (state == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }else if (t == getExclusiveOwnerThread()){
                setState(state+1);
                return true;
            }
            return false;
        }


        //1.先看是不是当前线程，如果不是就不能释放，抛出异常

        //2.如果是的话，判断state，如果state-1（arg）=0，那就可以释放


        @Override
        protected boolean tryRelease(int arg) {
            //锁的获取和释放需要一一对应，那么调用这个方法的线程，一定是当前线程
            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new RuntimeException();
            }
            int state = getState() - arg;
            setState(state);
            if(state == 0){
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;

        }
        //ConditionObject不能在外部使用是AQS里的
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    Helper helper = new Helper();

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }

}
