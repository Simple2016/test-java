package lqw.test.test_spring_aop;

import lqw.test.test_spring_aop.auto.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-auto.xml"})
public class TestAutoAspj {

    @Autowired
    private TestService testService;

    @Test
    public void test1() throws InterruptedException {
        testService.addPerson("xiaoming");
        //testService.deletePerson("xiaoming");
        //testService.editPerson("xiaoming");
    }

}
