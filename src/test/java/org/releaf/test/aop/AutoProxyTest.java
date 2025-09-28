package org.releaf.test.aop;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.service.WorldService;

public class AutoProxyTest {

    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        // 获取代理对象
        WorldService worldService = applicationContext.getBean("worldService",  WorldService.class);
        worldService.explode();
    }
}
