package lqw.test.test_stream;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/** 
 *
 * @ClassName   类名：JettyLaunch
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年12月2日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年12月2日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class JettyLaunch {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("D:\\WorkSpaceSamples\\test-dubbo\\src\\main\\sources\\web.xml");
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        server.start();
        server.join();
    }

}
