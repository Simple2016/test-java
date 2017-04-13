package lqw.test.test_junit;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/** 
 *
 * @ClassName   类名：App2
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年6月1日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年6月1日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class App {

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @BeforeClass
    public static void beforeClass1() {// should be static
        System.err.println("beforeClass");
    }

    @Before
    public void before1() {
        System.err.println("before");
    }

    @PerfTest(invocations = 2, threads = 1)
    @Test
    public void test1() {
        System.err.println("test1");
    }

    @PerfTest(invocations = 2, threads = 1)
    @Test
    public void test2() {
        System.err.println("test2");
    }

    @After
    public void after1() {
        System.err.println("after");
    }

    @AfterClass
    public static void afterClass1() {// should be static
        System.err.println("afterClass");
    }

}
