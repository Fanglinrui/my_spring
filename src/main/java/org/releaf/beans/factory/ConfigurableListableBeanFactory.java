package org.releaf.beans.factory;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.config.AutowireCapableBeanFactory;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据名称查找BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
