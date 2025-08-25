package org.releaf.test.ioc;

import org.junit.Test;
import org.releaf.beans.factory.support.DefaultListableBeanFactory;
import org.releaf.beans.factory.xml.XmlBeanDefinitionReader;
import org.releaf.test.bean.Car;
import org.releaf.test.bean.Person;
import org.releaf.test.common.CustomBeanFactoryPostProcessor;
import org.releaf.test.common.CustomBeanPostProcessor;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

    @Test
    public void testBeanFactoryPostProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 在所有BeanDefinition加载完成，bean实例化之前，修改
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        // 改了name
        assertThat(person.getName()).isEqualTo("zelda");

    }

    @Test
    public void testBeanPostProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 添加bean实例化后的处理器
        CustomBeanPostProcessor beanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);

        assertThat(car.getBrand()).isEqualTo("lamborghini");
    }
}
