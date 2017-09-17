package com.test.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组
 * <p>
 * AtomicLongArray{@link java.util.concurrent.atomic.AtomicIntegerArray}
 * <p>
 * AtomicReferenceArray{@link java.util.concurrent.atomic.AtomicReferenceArray}
 * <p>
 * AtomicIntegerArray{@link java.util.concurrent.atomic.AtomicIntegerArray}
 * <p>
 * Created by forever on 2017/9/17.
 */
public class AtomicArrayTest {
    static int[] value = new int[]{1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);//结果说明不会修改原数组
    }
}
