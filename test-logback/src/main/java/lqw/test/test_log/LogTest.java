package lqw.test.test_log;

import java.util.concurrent.atomic.AtomicInteger;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import lqw.test.test_log.inner.LogTestInner;
import lqw.test.test_log.inner.inner.LogTestInnerInner;
import lqw.test.test_log.inner2.LogTestInner2;

/**
 *
 * @ClassName 类名：LogTest
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2016年4月7日
 * @author 创建人： liqw
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2016年4月7日 liqw 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

public class LogTest {

    final static Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    volatile AtomicInteger i = new AtomicInteger(0);

    @Test
    @PerfTest(invocations = 1, threads = 10)
    public void test() throws Exception {
        int j = i.incrementAndGet();
        logger.error(j + "line1");
        logger.warn(j + "line2");
        logger.info(j + "line3");
        logger.debug(j + "line4");
        // if (i == 100) {
        // throw new InterruptedException();
        // }

        LogTestInner.log();
        LogTestInner2.log();
        LogTestInnerInner.log();

        // Thread.currentThread().sleep(500000000l);
    }

    @BeforeClass
    public static void before() {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.err.println("ShutdownHook1");
                LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
                lc.stop();
            }
        });

    }

    @AfterClass
    public static void after() throws InterruptedException {

    }

    public static void main(String... s) throws InterruptedException {
        logger.debug("debug");
        System.err.println("--------");
        // 打印logback状态，默认启动会自己打，不用调用
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
