package lqw.test.blog.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lqw.test.blog.domain.Group;
import lqw.test.blog.service.GroupService;
import lqw.test.blog.util.JsonHelper;
import lqw.test.blog.util.cacheHelper;

/** 
 *
 * @ClassName   类名：GroupController
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
@Controller
public class GroupController {

    final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "addGroup.do", method = RequestMethod.POST)
    public void addArticle(@RequestParam String groupName, HttpServletRequest request, HttpServletResponse response)
                    throws IOException {
        log.debug("---addGroup---");
        Group group = new Group();
        group.setGroupName(groupName);
        group.setCreateTime(new Date());
        // group.setAuthor(author);
        try {
        	if (cacheHelper.has(request.getSession().getId())) {
        		 groupService.addGroup(group);
                 response.getWriter().println("success");
    		}else{
    			 response.getWriter().println("fail");
    		}
          
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("fail");
        }

    }

    @RequestMapping(value = "queryGroup.do", method = RequestMethod.POST)
    public void queryArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("---quseryGroup---");
        List<Group> groupList = groupService.queryGroup();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", groupList);
        String group = JsonHelper.toJson(map);
        response.getWriter().println(group);
    }
}
