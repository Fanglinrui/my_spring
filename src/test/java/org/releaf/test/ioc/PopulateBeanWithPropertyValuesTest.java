package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.beans.PropertyValue;
import org.releaf.beans.PropertyValues;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.config.BeanReference;
import org.releaf.beans.factory.support.DefaultListableBeanFactory;
import org.releaf.test.bean.Car;
import org.releaf.test.bean.Person;

import static org.assertj.core.api.Assertions.assertThat;

public class PopulateBeanWithPropertyValuesTest {

    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "link"));
        propertyValues.addPropertyValue(new PropertyValue("age", 113));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person)beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("link");
        assertThat(person.getAge()).isEqualTo(113);

    }

    @Test
    public void testPopulateBeanWithBean() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册Car实例
        PropertyValues propertyValues4Car = new PropertyValues();
        propertyValues4Car.addPropertyValue(new PropertyValue("brand", "xiaomi"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, propertyValues4Car);
        beanFactory.registerBeanDefinition("car", carBeanDefinition);

        // 注册Person实例
        PropertyValues propertyValues4Person = new PropertyValues();
        propertyValues4Person.addPropertyValue(new PropertyValue("name", "link"));
        propertyValues4Person.addPropertyValue(new PropertyValue("age",113));
        // Person实例依赖Car实例
        propertyValues4Person.addPropertyValue(new PropertyValue("car",new BeanReference("car")));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues4Person);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person)beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName()).isEqualTo("link");
        assertThat(person.getAge()).isEqualTo(113);
        Car car = person.getCar();
        assertThat(car).isNotNull();
        assertThat(car.getBrand()).isEqualTo("xiaomi");

    }
}
