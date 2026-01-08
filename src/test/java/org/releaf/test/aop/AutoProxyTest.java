package org.releaf.test.aop;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.service.WorldService;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoProxyTest {

    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        // 获取代理对象
        WorldService worldService = applicationContext.getBean("worldService",  WorldService.class);
        worldService.explode();
    }

    @Test
    public void testPopulateProxyBeanWithPropertyValues() throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
        System.out.println(worldService.getClass().getName());
        assertThat(worldService.getName()).isEqualTo("earth");
    }
}
