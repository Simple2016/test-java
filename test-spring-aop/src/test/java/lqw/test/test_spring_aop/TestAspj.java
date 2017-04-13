package lqw.test.test_spring_aop;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class TestAspj {

	@Autowired
	private TargetClass testService;

	@org.junit.Test
	public void test1() throws InterruptedException {
		testService.targetMethod();
	}

}
