package lqw.test.test_spring_aop.perf;

import org.perf4j.aop.Profiled;
import org.springframework.stereotype.Service;

/**
 * Created by liqw on 2017/8/10.
 */
@Service
public class PerfTargetClass {
    @Profiled
    public void editPerson(String personName) {
        System.out.println("edit person " + personName);
    }
}
