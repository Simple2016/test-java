package com.test.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子更新基本类型
 * <p>
 * AtomicBoolean{@link java.util.concurrent.atomic.AtomicBoolean}
 * <p>
 * AtomicInteger{@link java.util.concurrent.atomic.AtomicInteger}
 * <p>
 * AtomicLong{@link java.util.concurrent.atomic.AtomicLong}
 * <p>
 * Created by forever on 2017/9/17.
 */
public class AtomicIntegerTest {
    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }
}
