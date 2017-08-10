package lqw.test.testaspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by liqw on 2017/8/8.
 */
//@Aspect
public class MyAspect {
//    @Pointcut("execution(@DebugTrace * *..*.*(..))")
    public void DebugTraceMethod() {
    }

//    @Before("DebugTraceMethod()")
    public void beforeDebugTraceMethod(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        System.out.println("beforeDebugTraceMethod: " + key);
    }
}
