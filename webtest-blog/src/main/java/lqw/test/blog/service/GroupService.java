package lqw.test.blog.service;

import java.util.List;

import lqw.test.blog.domain.Group;

/** 
 *
 * @ClassName   类名：GroupService
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

public interface GroupService {

    void addGroup(Group group);

    List<Group> queryGroup();

}
