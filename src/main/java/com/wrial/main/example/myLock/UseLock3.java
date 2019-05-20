package com.wrial.main.example.myLock;

/*
 *  使用AQS自定义的锁，不可重入锁
 *
 * */
public class UseLock3 {

    private int value;

    MyLock3 lock = new MyLock3();

    //下边的代码没加锁会出现线程安全性问题
    /* public int getNext() {
         try {
             Thread.sleep(100);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         return value++;
     }*/
    public int getNext() {
        lock.lock();
        try {
            Thread.sleep(100);
            return value++;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        UseLock3 lock3 = new UseLock3();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread() + " " + lock3.getNext());
                }
            }).start();
        }
    }
}
