package lqw.test.test_jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 *
 * @ClassName 类名：PerformenceTest
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2016年4月18日
 * @author 创建人： liqw
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2016年4月18日 liqw 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

public class PerformenceTest extends AbstractJavaSamplerClient {

	private SampleResult results;

	private static int number = 1000000000;

	// 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("startEInvoiceNumber", "1000000000");// 设置参数
		return params;
	}

	// 初始化
	public void setupTest(JavaSamplerContext arg0) {
		String startNum = arg0.getParameter("startEInvoiceNumber");
		number = Integer.parseInt(startNum);

	}

	public SampleResult runTest(JavaSamplerContext arg0) {
		results = new SampleResult();
		results.setSampleLabel("test");
		results.sampleStart();// jmeter 开始统计响应时间标记
		try {
			String eInvoiceNumber = String.valueOf(number);
			HttpClientTest.generateSignXml(eInvoiceNumber);
			HttpClientTest.issue(eInvoiceNumber);

			number++;
			results.setSuccessful(true);
			// 被测对象调用
		} catch (Throwable e) {
			results.setSuccessful(false);
			e.printStackTrace();
		} finally {
			results.sampleEnd();// jmeter 结束统计响应时间标记
		}
		return results;
	}

	@Override
	public void teardownTest(JavaSamplerContext context) {
		// TODO Auto-generated method stub
		super.teardownTest(context);
	}

	public static void main(String[] args) {
		Arguments arg = new Arguments();
		arg.addArgument("startEInvoiceNumber", "1000000000");
		JavaSamplerContext j = new JavaSamplerContext(arg);
		PerformenceTest test = new PerformenceTest();
		test.setupTest(j);
		test.runTest(j);
	}
}
