package lqw.test.test_spring_aop.auto;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void addPerson(String personName) {
        System.out.println("add person " + personName);
    }

    public void deletePerson(String personName) {
        System.out.println("delete person " + personName);
    }

    public void editPerson(String personName) {
        System.out.println("edit person " + personName);
        throw new RuntimeException("edit person throw exception");
    }
}
