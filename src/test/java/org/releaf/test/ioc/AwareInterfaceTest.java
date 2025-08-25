package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.service.HelloService;

import static org.assertj.core.api.Assertions.assertThat;

public class AwareInterfaceTest {

    @Test
    public void test() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = context.getBean("helloService",HelloService.class);
        assertThat(helloService.getApplicationContext()).isNotNull();
        assertThat(helloService.getBeanFactory()).isNotNull();
    }
}
