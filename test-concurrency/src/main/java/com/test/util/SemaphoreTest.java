package com.test.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 控制并发线程数的Semaphore,用于做流量控制
 * Created by forever on 2017/9/17.
 * 30哥线程但只有10个令牌，所以最大只能同时执行10个，其他线程等待
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(10);
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.printf("现在运行了%s个业务\r\n", atomicInteger.getAndIncrement());
                        Thread.sleep(3000);//模拟执行业务
                        atomicInteger.getAndDecrement();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        threadPool.shutdown();
    }
}
