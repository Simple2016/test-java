package lqw.test.cache.google_cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    private static LoadingCache<String, Object> cache = CacheBuilder
            .newBuilder().expireAfterWrite(6000, TimeUnit.SECONDS)
            .build(new ACacheLoader());

    public static class ACacheLoader extends CacheLoader {
        Logger logger = LoggerFactory.getLogger(CacheLoader.class);

        @Override
        public Object load(Object key) {
            logger.info("load key :{}", key);
            return key.hashCode();
        }
    }

    public static void main(String[] args) throws ExecutionException {
        System.out.println(cache.get("a"));
        System.out.println(cache.get("a"));
        System.out.println(cache.get("a"));
        System.out.println(cache.get("a"));
        cache.refresh("a");
        //System.out.println(cache.get("a"));
    }
}
