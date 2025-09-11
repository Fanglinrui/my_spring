package org.releaf.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 保存和管理代理对象需要的配置信息
 */
public class AdvisedSupport {

    //是否使用cglib代理
    private boolean proxyTargetClass = false;

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() { return proxyTargetClass; }

    public void setProxyTargetClass(boolean proxyTargetClass) { this.proxyTargetClass = proxyTargetClass; }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
