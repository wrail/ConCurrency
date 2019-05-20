package com.wrial.main.example.myLock;

/*
 * 使用自定义锁
 *
 * */
public class UseLock1 {

    Mylock1 mylock1 = new Mylock1();
    private int value = 0;

    public int getNext() {
        mylock1.lock();
        value++;
        value++;
        mylock1.unlock();
        return value;
    }

    public static void main(String[] args) {

        UseLock1 sequence = new UseLock1();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(sequence.getNext());
                }
            }).start();

        }
    }
}