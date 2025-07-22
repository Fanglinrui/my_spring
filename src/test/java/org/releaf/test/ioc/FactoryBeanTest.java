package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.beans.factory.FactoryBean;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.ioc.bean.Car;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryBeanTest {

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

        Car car = applicationContext.getBean("car", Car.class);
        assertThat(car.getBrand()).isEqualTo("porsche");
    }

}
