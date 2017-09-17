package com.test.util;

import java.util.concurrent.CountDownLatch;

/**
 * Created by forever on 2017/9/17.
 * <p>
 * 等待多线程完成的 {@link CountDownLatch}
 */
public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finish");
                countDownLatch.countDown();
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
                countDownLatch.countDown();
            }
        });


        parser1.start();
        System.out.println("parser2 start");
        parser2.start();

        countDownLatch.await();
        System.out.println("all parser finish");
    }
}
