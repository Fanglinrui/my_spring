package org.releaf.beans.factory.support;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.config.BeanDefinition;

/**
 *  Bean 的实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
