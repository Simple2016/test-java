package lqw.test.test_redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;

/** 
 *
 * @ClassName   类名：RedisTest
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年4月7日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年4月7日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class RedisTest {

    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) {
        JedisConnMamager jedisManager = (JedisConnMamager) context.getBean(JedisConnMamager.class);
        Jedis jedis = jedisManager.getJedis();
        jedis.incr("lqw");
        jedis.expire("lqw", 10);//设置key的生存时间
        System.err.println("ok");
    }
}
