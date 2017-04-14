package lqw.test.test_log4j;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

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

    AtomicInteger i = new AtomicInteger(1);

    @Test
    @PerfTest(invocations = 10, threads = 10)
    public void test() throws InterruptedException {

        logger.error(getLine() + "line");
        logger.info(getLine() + "line");
        logger.warn(getLine() + "line");
        logger.debug(getLine() + "line");
        // LogTestInner.main(null);

    }

    public int getLine() {
        return i.incrementAndGet();
    }

    @AfterClass
    public static void after() throws InterruptedException {

    }

    public static void main(String... s) throws InterruptedException {

    }
}
