package lqw.test.blog.dao;

import org.apache.ibatis.annotations.Param;

import lqw.test.blog.domain.User;

/** 
 *
 * @ClassName   类名：UserDao
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

public interface UserDao {

    public void register(User user);

    public User queryUserByUserName(@Param("name") String userName);

    public void delUser(@Param("id") String userId);

    public void updateUser(User user);
}
