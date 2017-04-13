package lqw.test.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lqw.test.blog.domain.Group;
import lqw.test.blog.domain.User;

/** 
 *
 * @ClassName   类名：GroupDao
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年11月22日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年11月22日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public interface GroupDao {

    public User queryGroupByName(@Param("groupName") String groupName);

    public List<Group> queryGroup();

    public void addGroup(Group group);

    public void delGroup(@Param("groupName") String groupName);

    public void modifyGroup(Group group);

}
