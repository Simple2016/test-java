package lqw.test.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liqw on 2017/10/13.
 */
public class TestJsonlib {
    Logger logger = LoggerFactory.getLogger(TestJsonlib.class);
    TestJsonBean testJsonBean = new TestJsonBean();

    @Before
    public void before() {
        testJsonBean.setB(true);
        testJsonBean.setI(9);
        //此处出错
//        testJsonBean.setS("[{\"a\":\"string\",\"i\":9,\"b\":true}]");//java.lang.ClassCastException@3cb15770- had objects of type "[Ljava.lang.String;" but expected signature"java.lang.String"
        testJsonBean.setS("string");
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        testJsonBean.setStrings(strings);
    }

    @Test
    public void TestGson() {

        //对象与json 互转
        String s = JSONObject.fromObject(testJsonBean).toString();
        logger.info("JSONObject.fromObject:{}==>{}", testJsonBean, s);
        TestJsonBean testJsonBean1 = (TestJsonBean) JSONObject.toBean(JSONObject.fromObject(s), TestJsonBean.class);
        logger.info("JSONObject.toBean:{}==>{}", s, testJsonBean1);
        //list 与 json 互转
        List<TestJsonBean> testJsonBeanList = new ArrayList<>();
        testJsonBeanList.add(testJsonBean);
        testJsonBeanList.add(testJsonBean1);
        String l = JSONArray.fromObject(testJsonBeanList).toString();
        logger.info("JSONArray.fromObject:{}==>{}", testJsonBeanList, l);
        List<TestJsonBean> arrayLists = (List<TestJsonBean>) JSONArray.toList(JSONArray.fromObject(l), TestJsonBean.class);
        logger.info("JSONArray.toList:{}==>{}", l, arrayLists);
    }

    @Test
    public void testJsonConfig() {
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"s"});

        String s = JSONObject.fromObject(testJsonBean, config).toString();
        logger.info("JSONObject.fromObject:{}==>{}", testJsonBean, s);

    }

    //jsonlib 要求必须是public
    public static class TestJsonBean {
        private String s;
        private int i;
        private boolean b;
        private List<String> strings;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public boolean isB() {
            return b;
        }

        public void setB(boolean b) {
            this.b = b;
        }

        public List<String> getStrings() {
            return strings;
        }

        public void setStrings(List<String> strings) {
            this.strings = strings;
        }

        @Override
        public String toString() {
            return "TestJsonBean{" +
                    "s='" + s + '\'' +
                    ", i=" + i +
                    ", b=" + b +
                    ", strings=" + strings +
                    '}';
        }
    }

}
