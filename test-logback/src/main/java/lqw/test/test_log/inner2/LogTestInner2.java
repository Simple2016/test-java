package lqw.test.test_log.inner2;

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

public class LogTestInner2 {

    static final Logger logger = LoggerFactory.getLogger(LogTestInner2.class);

    public static void log() {
        logger.error("inner2 error");
        logger.warn("inner2 warn");
        logger.info("inner2 info");
        logger.debug("inner2 debug");
    }

}
