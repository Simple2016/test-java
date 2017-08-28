package lqw.test.react.controller;

/**
 * Created by liqw on 2017/8/28.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "post.do", method = RequestMethod.GET)
    public void post() {
        System.err.println("aaa");
    }

}

