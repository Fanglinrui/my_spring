package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;

public class InitAndDestroyMethodTest {

    @Test
    public void testInitAndDestroyMethod() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        context.registerShutdownHook();
    }
}
