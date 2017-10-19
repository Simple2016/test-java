package lqw.test.cache.eh_cache.Service;

import lombok.extern.slf4j.Slf4j;
import lqw.test.cache.eh_cache.domain.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liqw on 2017/10/19.
 */
@Service
@Slf4j
public class MockUserCenterService {
    Map<String, User> mockRepository = new HashMap<>();

    //c
    @CachePut(value = "user_center_service", key = "#user.id")//会把user.id作为key，返回值作为value放入缓存；
    public User add(User user) {
        log.info("MockUserCenterService add");
        mockRepository.put(user.getId(), user);
        printMockRepository();
        return user;
    }

    //u
    @CachePut(value = "user_center_service", key = "#user.id")
    public User update(User user) {
        log.info("MockUserCenterService update");
        mockRepository.remove(user.getId());
        mockRepository.put(user.getId(),user);
        printMockRepository();
        return user;
    }

    //r
    @Cacheable(value = "user_center_service")
    public User get(String id) {
        log.info("MockUserCenterService get:{}", id);
        return mockRepository.get(id);
    }

    //d
    @CacheEvict(value = "user_center_service", key = "#user.id")
    public void delete(User user) {
        log.info("MockUserCenterService delete");
        mockRepository.remove(user.getId());
        printMockRepository();
    }

    @CacheEvict(value = "user_center_service", allEntries = true) //移除所有数据
    public void deleteAll() {
        log.info("MockUserCenterService deleteAll");
        mockRepository.clear();
        printMockRepository();
    }

    private void printMockRepository() {
        log.info("MockRepository==>{}", mockRepository.toString());
    }

}
