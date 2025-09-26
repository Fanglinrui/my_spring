package org.releaf.test.common;

import org.releaf.aop.AfterAdvice;

import java.lang.reflect.Method;

public class WorldServiceAfterAdvice implements AfterAdvice {
    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterAdvice : do sth after the earth explodes");
    }
}
