package lqw.test.blog.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/** 
 *
 * @ClassName   类名：jsonHelper
 * @Description 功能说明：
 * <p>
 * TODO
 * </p>
 ************************************************************************
 * @date        创建日期：2016年11月8日
 * @author      创建人： liqw
 * @version     版本号：V1.0
 * <p>
 ***************************修订记录*************************************
 * 
 *   2016年11月8日   liqw  创建该类功能。
 *
 ***********************************************************************
 * </p>
 */

public class JsonHelper {

    /*
     * public static <T> T toClassOfT(String json, Class<T> classOfT) { GsonBuilder gsonBuilder = new GsonBuilder();
     * Gson gson = gsonBuilder.enableComplexMapKeySerialization().serializeNulls().create(); Type type = new
     * TypeToken<T>() { }.getType(); return gson.fromJson(json, type); }
     */

    public static <T> T fromJson(String json, Class<T> classOfT) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object sp) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Type type = new TypeToken<Object>() {
        }.getType();

        // ServicePattern target = gson.fromJson(sp, type);
        return gson.toJson(sp, type);
    }

    public static void main(String... strings) {
        User u = new JsonHelper().new User();
        u.id = 1;
        u.name = "xiaoming";
        System.err.println("实体转json->" + toJson(u));
        System.err.println("json转实体->" + fromJson(toJson(u), User.class).toString());
        List<User> list = new ArrayList<User>();
        list.add(u);
        list.add(u);
        System.err.println("list转json->" + toJson(list));
        System.err.println("json转list->" + fromJson(toJson(list), ArrayList.class).toString());
    }

    private class User {

        int id;

        String name;

        String age;

        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
        }

    }

}
