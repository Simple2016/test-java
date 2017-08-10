package lqw.test.testaspectj;


import org.perf4j.aop.Profiled;

/**
 * Hello world!
 */
public class App {
//    static Logger logger = LoggerFactory.getLogger(App.class);

    @DebugTrace
    public static void main(String[] args) {
        System.out.println("Hello World!");
        test();
    }

    @Profiled
    public static String test() {
//        logger.info("1");
        return "test_return";
    }

}
