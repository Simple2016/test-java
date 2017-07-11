package lqw.test.test_spring_aop;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lqw.test.test_spring_aop.auto.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class TestAutoAspj {

	@Autowired
	private TestService testService;

	@org.junit.Test
	public void test1() throws InterruptedException {
		testService.addPerson("xiaoming");
		//testService.deletePerson("xiaoming");
		//testService.editPerson("xiaoming");
	}

}
