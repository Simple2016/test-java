package lqw.test.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lqw.test.blog.dao.UserDao;
import lqw.test.blog.domain.User;
import lqw.test.blog.service.UserService;

/** 
 *
 * @ClassName   类名：UserServiceImpl
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月21日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月21日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Boolean loginCheck(User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            System.err.println("输入的用户名或密码为空!");
            return false;
        }
        User u = userDao.queryUserByUserName(user.getUserName());
        if (u != null && user.getPassword().equals(u.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean register(User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            System.err.println("输入的用户名或密码为空!");
            return false;
        }
        User theuser = userDao.queryUserByUserName(user.getUserName());
        if (theuser == null) {
            userDao.register(user);
            return true;
        } else {
            System.err.println("用户已存在:" + theuser.toString());
            return false;
        }
    }

}