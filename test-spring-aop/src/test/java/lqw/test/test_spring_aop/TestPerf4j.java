package lqw.test.test_spring_aop;

import lqw.test.test_spring_aop.auto.TestService;
import lqw.test.test_spring_aop.perf.PerfTargetClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-perf.xml"})
public class TestPerf4j {

    @Autowired
    private PerfTargetClass perfTarget;

    @Test
    public void test1() throws InterruptedException {
        perfTarget.editPerson("xiaoming");
    }

}
