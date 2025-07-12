package org.releaf.beans.factory.support;

import org.releaf.beans.BeansException;
import org.releaf.core.io.DefaultResourceLoader;
import org.releaf.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());

    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    public void setResourceLoader(ResourceLoader resourceLoader) { this.resourceLoader = resourceLoader; }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
