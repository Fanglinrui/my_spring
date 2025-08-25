package org.releaf.aop;

/**
 *   切点抽象
 *
 *
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
