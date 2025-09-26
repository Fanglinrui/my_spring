package org.releaf.test.common;

import org.releaf.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class WorldServiceAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterReturningAdvice : do sth after the Earth explodes Return");
    }
}
