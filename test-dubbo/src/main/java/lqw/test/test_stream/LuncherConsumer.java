package lqw.test.test_stream;

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

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        LuncherConsumer luncher = new LuncherConsumer();
        luncher.start();
    }

    void start() throws FileNotFoundException {
        String configLocation = "spring/dubbo-consumer2.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        DemoService ds = context.getBean(DemoService.class);
        String[] names = context.getBeanDefinitionNames();
        System.out.print("Beans:");
        for (String string : names) {
            System.out.print(string);
            System.out.print(",");
        }
        System.out.println();

        InputStream in = new FileInputStream(new File("C:/Setup.log"));
        System.out.println(ds.upload("", in));
    }
}