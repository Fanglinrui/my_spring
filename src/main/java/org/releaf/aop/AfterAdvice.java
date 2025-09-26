package org.releaf.aop;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * 后置增强
 */
public interface AfterAdvice extends Advice {
    void after(Method method, Object[] args, Object target) throws Throwable;
}
