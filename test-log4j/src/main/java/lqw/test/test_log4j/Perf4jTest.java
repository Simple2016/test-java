package lqw.test.test_log4j;

import org.perf4j.StopWatch;
import org.perf4j.aop.Profiled;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Perf4jTest {

    final static Logger logger = LoggerFactory.getLogger(Perf4jTest.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("start");
        // for (; ; ) {
//        method1();
//        method2();
//        method3();
        profiledExample("aaa");
        Thread.sleep(1000);
        //}

    }

    //@Profiled(tag = "dynamicTag_{$0}")
    @Profiled(message = "test_{$return}")
    public static String profiledExample(String tagSuffix) {
        logger.info("test");
        return "haha";
    }


    /**
     * 监控一处代码示例
     *
     * @throws InterruptedException
     */

    private static void method1() throws InterruptedException {

        //创建一个监控对象，这里使用LoggingStopWatch，它是把结果直接输出到控制台。我们也可以

        //使用StopWatch的其他子类，比如：Log4JStopWatch，CommonsLogStopWatch。不过这些子类需

        //要工程使用日志框架

        StopWatch stopWatch = new Slf4JStopWatch("codeBlock1");


        //这里就是一些需要监控的代码，我们命名为codeBlock1

        //使用线程休眠是为了模拟代码执行时间

        Thread.sleep((long) (Math.random() * 1000L));


        //停止计算代码性能

        stopWatch.stop();

    }


    /**
     * 一个方法多出代码监控
     *
     * @throws InterruptedException
     */

    private static void method2() throws InterruptedException {

        StopWatch stopWatch = new Slf4JStopWatch();

        Thread.sleep((long) (Math.random() * 1000L));

        stopWatch.lap("codeBlock3");

        Thread.sleep((long) (Math.random() * 1000L));

        stopWatch.lap("codeBlock4");

        Thread.sleep((long) (Math.random() * 1000L));

        stopWatch.lap("codeBlock5");

        Thread.sleep((long) (Math.random() * 1000L));

        stopWatch.stop("codeBlock6");

    }


    /**
     * stop方法可以加入一些说明信息
     */

    private static void method3() {

        StopWatch stopWatch = new Slf4JStopWatch();

        try {

            // the code block being timed - this is just a dummy example

            long sleepTime = (long) (Math.random() * 1000L);

            Thread.sleep(sleepTime);

            if (sleepTime > 500L) {

                throw new Exception("Throwing exception");

            }

            stopWatch.stop("codeBlock2.success", "Sleep time was < 500 ms");

        } catch (Exception e) {

            stopWatch.stop("codeBlock2.failure", "Exception was: " + e);

        }

    }

}