package org.releaf.beans.factory.config;


import org.releaf.beans.BeansException;
import org.releaf.beans.factory.ObjectFactory;

/**
 *  单例注册表
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName) throws BeansException;

    Object getSingleton(String beanName, ObjectFactory<?> objectFactory) throws BeansException;

    void addSingleton(String beanName, Object singletonObject);
}
