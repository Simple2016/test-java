package lqw.test.test_spring_aop;

public class AspectClass {

    public void beforeMethod1() {
        System.out.println("目标方法之前1");
    }

    public void afterMethod1() {
        System.out.println("目标方法之后1");
    }

    public void beforeMethod2() {
        System.out.println("目标方法之前2(与配置的顺序是相同的)");
    }

    public void afterMethod2() {
        System.out.println("目标方法之后2");
    }
}
