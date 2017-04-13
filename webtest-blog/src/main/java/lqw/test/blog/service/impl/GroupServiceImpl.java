package lqw.test.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lqw.test.blog.dao.GroupDao;
import lqw.test.blog.domain.Group;
import lqw.test.blog.service.GroupService;

/** 
 *
 * @ClassName   类名：GroupServiceImpl
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

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    @Override
    public List<Group> queryGroup() {
        return groupDao.queryGroup();
    }
}
