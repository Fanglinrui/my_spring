package org.releaf.test.ioc.common;

import org.releaf.beans.BeansException;
import org.releaf.beans.PropertyValue;
import org.releaf.beans.PropertyValues;
import org.releaf.beans.factory.ConfigurableListableBeanFactory;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        // 修改name属性
        propertyValues.addPropertyValue(new PropertyValue("name", "zelda"));
    }
}
