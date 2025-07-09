package org.releaf.beans.factory;

import org.releaf.beans.BeansException;

/**
 * 获取bean
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
