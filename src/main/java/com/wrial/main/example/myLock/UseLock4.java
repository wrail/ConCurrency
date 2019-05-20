package com.wrial.main.example.myLock;

/*
 *  使用AQS自定义的可重入锁
 * */
public class UseLock4 {

    private int value;

    MyLock4 lock = new MyLock4();

    public int getNext() {
        lock.lock();
        try {
            Thread.sleep(100);
            return value++;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }

    }

    private void lockA() {
        lock.lock();
        System.out.println("lockA");
        lockB();
        lock.unlock();
    }

    private void lockB() {
        lock.lock();
        System.out.println("lockB");
        lock.unlock();
    }

    public static void main(String[] args) {

        UseLock4 lock3 = new UseLock4();
        lock3.lockA();


    }
}
