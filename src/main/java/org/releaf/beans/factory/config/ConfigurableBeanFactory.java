package org.releaf.beans.factory.config;

import org.releaf.beans.factory.HierarchicalBeanFactory;
import org.releaf.beans.factory.support.BeanDefinitionRegistry;
import org.releaf.util.StringValueResolver;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例bean
     */
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

}
