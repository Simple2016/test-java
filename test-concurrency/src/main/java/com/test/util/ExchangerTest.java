package com.test.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by forever on 2017/9/17.
 * 线程间交换数据的Exchanger，限于两个线程之间，用于遗传算法和校对工作
 */
public class ExchangerTest {
    private static final Exchanger<String> EXCHANGER = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {
            public void run() {
                String A = "银行流水A";//A录入银行的数据
                try {
                    String exchange = EXCHANGER.exchange(A);//阻塞直到有人交换
                    System.out.println("A收到:" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            public void run() {
                String B = "银行流水B";//BS录入银行的数据
                try {
                    String exchange = EXCHANGER.exchange(B);//阻塞直到有人交换
                    System.out.println("B收到:" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }
}
