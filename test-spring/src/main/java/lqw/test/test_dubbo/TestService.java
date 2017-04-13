package lqw.test.test_dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TestService {

    private final Logger log = LoggerFactory.getLogger(TestService.class);

    public void logTest() throws InterruptedException {
        for (int i = 1; i <= 1000; i++) {
            log.debug("echo");
            log.info("echo");
            log.warn("echo");
            log.error("echo");
            if (i == 100) {
                throw new InterruptedException();
            }
        }

    }
}
