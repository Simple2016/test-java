package lqw.test.test_spring_aop;

public class ExtendsClass {

    public void beforeMethod() {
        System.out.println("目标方法之前");
    }

    public void afterMethod() {
        System.out.println("目标方法之后");
    }

    public void beforeMethod1() {
        System.out.println("目标方法之前1(注意顺序)");
    }

    public void afterMethod1() {
        System.out.println("目标方法之后1");
    }
}
