package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.beans.BeansException;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.bean.Car;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ValueAnnotationTest {

    @Test
    public void testValueAnnotation() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");
        Car car = applicationContext.getBean("car", Car.class);
        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
