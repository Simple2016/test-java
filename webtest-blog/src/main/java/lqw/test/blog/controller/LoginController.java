package lqw.test.blog.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.zookeeper.server.SessionTracker.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lqw.test.blog.domain.User;
import lqw.test.blog.service.UserService;
import lqw.test.blog.util.cacheHelper;

/**
 *
 * @ClassName 类名：LoginController
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2016年11月8日
 * @author 创建人： liqw
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 *
 *          2016年11月8日 liqw 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
public class LoginController {

	final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		log.debug("---login---");
		HttpSession session=request.getSession();
		if(session.isNew()){
			log.debug("---new session---");
		}else {
			log.debug("---old session---");
		}
		String userName=request.getParameter("userName");
		String passwd=request.getParameter("password");
		User user=new User();
		user.setPassword(passwd);
		user.setUserName(userName);
		if(userService.loginCheck(user)){
			cacheHelper.setValue(session.getId());
			response.getWriter().write("ok");
		}else{
			response.getWriter().write("never");
		}
	}



	@RequestMapping(value = "register.do", method = RequestMethod.POST)
	public void register(HttpServletRequest request, HttpServletResponse response) {
		log.debug("---register---");
	}
}
