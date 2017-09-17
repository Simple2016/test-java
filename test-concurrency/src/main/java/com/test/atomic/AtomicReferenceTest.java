package com.test.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子更新引用类型
 * <p>
 * AtomicReference{@link java.util.concurrent.atomic.AtomicReference}
 * <p>
 * AtomicReferenceFieldUpdater{@link java.util.concurrent.atomic.AtomicReferenceFieldUpdater}
 * <p>
 * AtomicMarkableReference{@link java.util.concurrent.atomic.AtomicMarkableReference}
 * <p>
 * Created by forever on 2017/9/17.
 */
public class AtomicReferenceTest {
    static AtomicReference<User> ai = new AtomicReference<User>();

    public static void main(String[] args) {
        User user = new User("conan", 15);
        ai.set(user);
        User updateUser = new User("Shinichi", 17);
        ai.compareAndSet(user, updateUser);
        System.out.println(ai.get().getName());
        System.out.println(ai.get().getOld());
    }

    static class User {
        private String name;

        private int old;

        public String getName() {
            return name;
        }

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }

    }

}
