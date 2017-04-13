package lqw.test.test_dubbo;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

/**
 * @author Administrator
 *
 */
// @Repository
public class HookService {

    public HookService() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                System.err.println("===>stop LoggerContext");
                LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
                lc.stop();
            }
        }));
    }

}
