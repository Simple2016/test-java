package lqw.test.test_log.inner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class LogTestInner {

	static final Logger logger = LoggerFactory.getLogger(LogTestInner.class);

	public static void log() {
		logger.error("inner error");
		logger.warn("inner warn");
		logger.info("inner info");
		logger.debug("inner debug");
	}

}
