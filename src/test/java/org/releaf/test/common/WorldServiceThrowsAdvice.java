package org.releaf.test.common;

import org.releaf.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class WorldServiceThrowsAdvice implements ThrowsAdvice {
    @Override
    public void throwsHandle(Throwable throwable, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("ThrowsAdvice : do sth when the earth explodes func throws an exception");
    }
}
