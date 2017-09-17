package com.test.util;

/**
 * Created by forever on 2017/9/17.
 *
 * 线程如何等待其他线程
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finish");
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });


        parser1.start();
        parser1.join();
        System.out.println("parser2 start");
        parser2.start();
        parser2.join();
        System.out.println("all parser finish");
    }
}
