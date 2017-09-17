package com.test.atomic;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子更新字段类
 * <p>
 * AtomicIntegerFieldUpdater{@link java.util.concurrent.atomic.AtomicIntegerFieldUpdater}
 * <p>
 * AtomicLongFieldUpdater{@link java.util.concurrent.atomic.AtomicLongFieldUpdater}
 * <p>
 * AtomicStampedReference{@link java.util.concurrent.atomic.AtomicStampedReference}
 * <p>
 * Created by forever on 2017/9/17.
 */
public class AtomicIntegerFieldUpdaterTest {
    private static AtomicIntegerFieldUpdater<User> ai = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public static void main(String[] args) {
        User conan = new User("conan", 10);
        System.out.println(ai.getAndIncrement(conan));
        System.out.println(ai.get(conan));

    }

    static class User {
        private String name;

        public volatile int old;//注意

        public String getName() {
            return name;
        }

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public int getOld() {
            return old;
        }
    }

}
