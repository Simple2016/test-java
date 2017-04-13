package lqw.test.test_stream;

import java.io.FileInputStream;
import java.io.InputStream;

/** 
 *
 * @ClassName   类名：Demo
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年12月1日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年12月1日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */
public class DemoServiceImpl implements DemoService {

    public InputStream download(String path) {
        System.out.println(path + "******************************");
        try {
            InputStream inputStream = new FileInputStream(path);
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public String upload(String destPath, InputStream in) {
        try {
            // FileOutputStream out = new FileOutputStream("E:\\temp\\a.js");
            int n = -1;
            byte[] b = new byte[10240];
            while ((n = in.read(b)) != -1) {
                System.out.println(new String(b, 0, n, "utf-8"));
                // out.write(b, 0, n);
            }
            // out.close();
            // out.flush();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload complete !";
    }

}
