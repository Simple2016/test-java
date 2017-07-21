package lqw.test.test_jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liqw on 2017/7/18.
 */
public class JmeterTest extends AbstractJavaSamplerClient {
    private static AtomicInteger j = new AtomicInteger(0);
    private long start = 0;
    private long end = 0;
    private int i;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Thread thread = new TestThread();
            thread.run();
            // thread.join();
        }

    }

    /**
     * JMeter测试用例入口
     */
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult results = new SampleResult();
        results.sampleStart();
        //执行测试代码
        System.out.println(Thread.currentThread().getId() + "-" + i++ + "-" + j.incrementAndGet());
        results.setSuccessful(true); // 执行成功或失败
        results.sampleEnd();
        return results;
    }

    /**
     * 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter界面的参数列表中
     */
    @Override
    public Arguments getDefaultParameters() {
        System.out.println("getDefaultParameters");
        Arguments params = new Arguments();
        params.addArgument("startNumber", "1000000000");// 设置参数
        return params;
    }

    /**
     * 执行runTest()方法前会调用此方法,可放一些初始化代码,每个线程会调用一次
     */
    @Override
    public void setupTest(JavaSamplerContext arg0) {
        System.out.println("setupTest");
        String startNum = arg0.getParameter("startNumber");//获取设置的参数
        i = Integer.parseInt(startNum);
        start = System.currentTimeMillis();
    }

    /**
     * 执行runTest()方法后会调用此方法.每个线程结束会调用一次
     */
    @Override
    public void teardownTest(JavaSamplerContext arg0) {
        // 结束时间
        end = System.currentTimeMillis();
        // 总体耗时
        System.err.println("cost time(s):" + (end - start) / 1000);
    }

    static class TestThread extends Thread {
        public void run() {
            Arguments arg = new Arguments();
            arg.addArgument("startNumber", "1000000000");
            JavaSamplerContext j = new JavaSamplerContext(arg);
            JmeterTest test = new JmeterTest();
            test.setupTest(j);
            test.runTest(j);
        }
    }
}
