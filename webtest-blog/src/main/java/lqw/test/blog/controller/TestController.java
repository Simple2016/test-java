package lqw.test.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 *
 * @ClassName   类名：testController
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年10月11日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年10月11日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */
@Controller
public class TestController {

    @RequestMapping(value = "manage/test.do/{json}.do", method = RequestMethod.GET)
    public void postas(@PathVariable String json, HttpServletRequest request, HttpServletResponse response) {
        System.err.println("heheheheh");
        System.err.println(json);
    }

}
