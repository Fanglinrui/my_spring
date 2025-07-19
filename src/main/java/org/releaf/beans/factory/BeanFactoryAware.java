package org.releaf.beans.factory;

import org.releaf.beans.BeansException;

/**
 * 实现该接口，以感知所属的beanFactory
 *
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
