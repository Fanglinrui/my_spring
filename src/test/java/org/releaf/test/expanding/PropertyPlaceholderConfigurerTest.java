package org.releaf.test.expanding;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.bean.Car;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertyPlaceholderConfigurerTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

        Car car = context.getBean("car", Car.class);
        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
