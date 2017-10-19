package lqw.test.cache.eh_cache;

/**
 * Created by liqw on 2017/10/19.
 */
public class JavaApp {
    public static void main(String[] args) {
        EhcacheUtil instance = EhcacheUtil.getInstance();
        String cacheName = "user_center_service";
        instance.put(cacheName, "a", "b");
        Object a = instance.get(cacheName, "a");
        System.out.println(a);
        instance.remove(cacheName,"a");
        System.out.println(instance.get(cacheName, "a"));
    }
}
