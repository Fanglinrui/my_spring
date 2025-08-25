package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.bean.Car;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeBeanTest {

    @Test
    public void testPrototype(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");

        Car car1 = context.getBean("car",Car.class);
        Car car2 = context.getBean("car",Car.class);
        assertThat(car1 != car2).isTrue();

    }
}
