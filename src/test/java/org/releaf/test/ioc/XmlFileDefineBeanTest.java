package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.beans.factory.support.DefaultListableBeanFactory;
import org.releaf.beans.factory.xml.XmlBeanDefinitionReader;
import org.releaf.test.ioc.bean.Car;
import org.releaf.test.ioc.bean.Person;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlFileDefineBeanTest {


    @Test
    public void testXmlFile() throws Exception {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("link");
        assertThat(person.getCar().getBrand()).isEqualTo("porsche");

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        assertThat(car.getBrand()).isEqualTo("porsche");

    }
}
