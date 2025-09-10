package org.releaf.test.aop;

import org.junit.Before;
import org.junit.Test;
import org.releaf.aop.AdvisedSupport;
import org.releaf.aop.MethodMatcher;
import org.releaf.aop.TargetSource;
import org.releaf.aop.aspectj.AspectJExpressionPointcut;
import org.releaf.aop.framework.CglibAopProxy;
import org.releaf.aop.framework.JdkDynamicAopProxy;
import org.releaf.test.common.WorldServiceInterceptor;
import org.releaf.test.service.WorldService;
import org.releaf.test.service.WorldServiceImpl;

public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.releaf.test.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

    }


    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

}
