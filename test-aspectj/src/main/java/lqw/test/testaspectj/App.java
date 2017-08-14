package lqw.test.testaspectj;


import org.perf4j.aop.Profiled;

import java.util.Random;

/**
 * Hello world!
 */
public class App {


    @DebugTrace
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello World!" + 0 / 0.0);
        for (int i = 0; i < 100; i++) {
            System.out.println(test());
            System.out.println(test1());
            System.out.println(test2());
        }
    }

    @Profiled
    public static String test() throws InterruptedException {

        Thread.sleep((long) (Math.random() * 100));
        return "test_return";
    }

    @Profiled
    public static String test1() throws InterruptedException {
        Random random = new Random();
        Thread.sleep((long) (Math.random() * 100));
        return "test1_return";
    }

    @Profiled
    public static String test2() throws InterruptedException {
        Random random = new Random();

        Thread.sleep((long) (Math.random() * 100));
        return "test2_return";


    }
}