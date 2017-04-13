package lqw.test;

import java.lang.reflect.Field;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IllegalAccessException {
        RestRequestParamsBean restRequestParamsBean = new RestRequestParamsBean();
        restRequestParamsBean.setApp_id("11");
        restRequestParamsBean.setDatetime("22");
        Field[] fields = restRequestParamsBean.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            System.out.print("成员变量" + i + "类型 : " + fields[i].getType().getName());
            System.out.print("\t成员变量" + i + "变量名: " + fields[i].getName() + "\t");
            System.out.println("成员变量" + i + "值:" + fields[i].get(restRequestParamsBean));
        }
    }

    private static class RestRequestParamsBean {
        String app_id;
        String datetime;

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }
}
