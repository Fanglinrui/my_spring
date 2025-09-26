package org.releaf.test.common;

import org.releaf.aop.BeforeAdvice;

import java.lang.reflect.Method;

public class WorldServiceBeforeAdvice implements BeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice : do sth before the earth explodes");
    }
}
