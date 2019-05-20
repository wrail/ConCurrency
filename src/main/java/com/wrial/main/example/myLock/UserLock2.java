package com.wrial.main.example.myLock;

/*
* 使用自己写的可重入锁
*
* */

public class UserLock2 {

    private int value = 0;
    MyLock2 lock2 = new MyLock2();

    public void lockA() {
        lock2.lock();
        System.out.println("LockA"+"第"+(++value)+"层");
        lockB();
        lock2.unlock();
        System.out.println("解锁第"+(value--)+"层");

    }

    public void lockB() {
        lock2.lock();
        System.out.println("LockB"+"第"+(++value)+"层");
        lock2.unlock();
        System.out.println("解锁第"+(value--)+"层");

    }


    public static void main(String[] args) {
        UserLock2 userLock2 = new UserLock2();
        userLock2.lockA();

    }


}
