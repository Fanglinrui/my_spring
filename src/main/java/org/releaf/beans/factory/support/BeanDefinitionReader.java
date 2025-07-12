package org.releaf.beans.factory.support;

import org.releaf.beans.BeansException;
import org.releaf.core.io.Resource;
import org.releaf.core.io.ResourceLoader;

/**
 * 读取 BeanDefinition 的接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
