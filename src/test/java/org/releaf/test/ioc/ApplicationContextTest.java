package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.context.support.ClassPathXmlApplicationContext;
import org.releaf.test.ioc.bean.Car;
import org.releaf.test.ioc.bean.Person;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextTest {

    @Test
    public void testApplicationContext() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        assertThat(person.getName()).isEqualTo("zelda");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);

        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
