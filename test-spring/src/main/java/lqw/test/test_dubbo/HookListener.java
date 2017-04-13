package lqw.test.test_dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.LoggerContext;

/**
 * @author Administrator
 *
 */
@Component
public class HookListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.debug("===>add hook");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                LOG.debug("===>stop LoggerContext");
                LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
                lc.stop();
            }
        }));
    }

}