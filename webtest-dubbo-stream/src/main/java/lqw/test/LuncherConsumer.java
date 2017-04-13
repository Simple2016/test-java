package lqw.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class LuncherConsumer {

    public static void main(String[] args) throws Exception {
        String configLocation = "dubbo-consumer.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        DemoService demoService = context.getBean(DemoService.class);
        String[] names = context.getBeanDefinitionNames();
        System.out.print("Beans:");
        for (String string : names) {
            System.out.print(string);
            System.out.print(",");
        }
        System.out.println();

        // upload(demoService);
        download(demoService);
    }

    static void upload(DemoService demoService) throws FileNotFoundException {

        InputStream in = new FileInputStream(new File("C:/Setup.log"));
        System.out.println(demoService.upload("", in));
    }

    // 调用upload
    public static void download(DemoService demoService) throws Exception {
        String srcPath = "C:/Setup.log";
        InputStream inputStream = demoService.download(srcPath); // 执行远程方法
        byte b[] = new byte[1024];
        int n;
        try {
            while ((n = inputStream.read(b)) != -1) {
                System.out.print(new String(b, 0, n, "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }
}