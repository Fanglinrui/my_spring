package org.releaf.aop;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * 后置
 *
 */
public interface AfterReturningAdvice extends Advice {
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
