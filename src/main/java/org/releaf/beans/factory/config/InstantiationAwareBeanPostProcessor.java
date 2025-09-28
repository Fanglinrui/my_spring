package org.releaf.beans.factory.config;

import org.releaf.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在bean实例化之前执行
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException;
}
