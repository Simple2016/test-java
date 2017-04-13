// package lqw.test.blog;
//
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import lqw.test.blog.domain.User;
// import lqw.test.blog.service.UserService;
//
/// **
// *
// * @ClassName 类名：Test
// * @Description 功能说明：
// * <p>
// * TODO
// * </p>
// ************************************************************************
// * @date 创建日期：2016年10月21日
// * @author 创建人： liqw
// * @version 版本号：V1.0
// * <p>
// ***************************修订记录*************************************
// *
// * 2016年10月21日 liqw 创建该类功能。
// *
// ***********************************************************************
// * </p>
// */
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "classpath:applicationContext.xml")
// public class UserServiceTest {
//
// @Autowired
// private UserService userService;
//
// // @Test
// public void testLoginCheck() {
// User user = new User();
// user.setUserName("lqw");
// user.setPassword("123");
// if (null != userService.loginCheck(user))
// System.out.println("------OK!!-----");
// else
// System.out.println("------Sorry!!-----");
// }
//
// // @Test
// public void testRegister() {
// User user = new User();
// user.setId(2);
// user.setUserName("manager1");
// user.setPassword("123");
// user.setType(1);
// System.out.println(userService.register(user));
// }
//
// }
