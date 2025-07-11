package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.beans.PropertyValue;
import org.releaf.beans.PropertyValues;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.support.DefaultListableBeanFactory;
import org.releaf.test.ioc.bean.Person;

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
}
