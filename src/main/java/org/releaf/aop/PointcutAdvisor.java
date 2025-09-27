package org.releaf.aop;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
