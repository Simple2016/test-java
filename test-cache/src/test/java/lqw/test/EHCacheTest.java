package lqw.test;

import lombok.extern.slf4j.Slf4j;
import lqw.test.cache.eh_cache.Service.MockUserCenterService;
import lqw.test.cache.eh_cache.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
@Slf4j
public class EHCacheTest {
    ClassPathXmlApplicationContext context;
    MockUserCenterService service;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = context.getBean(MockUserCenterService.class);
    }

    @Test
    public void test1() {
        User user = new User("1", "Jhon");
        User user1 = new User("1", "Mack");
        User user2 = new User("3", "Lily");
        service.add(user);
        service.add(user1);//key 相同会覆盖
        service.add(user2);
        log.info("@@get 1:" + service.get("1"));
        service.update(user);
        log.info("@@get 1:" + service.get("1"));
        service.delete(user);
        log.info("@@get 1:" + service.get("1"));
        service.deleteAll();
        log.info("@@get 3:" + service.get("3"));

    }

}
