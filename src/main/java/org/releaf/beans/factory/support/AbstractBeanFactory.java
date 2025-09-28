package org.releaf.beans.factory.support;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.FactoryBean;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.config.BeanPostProcessor;
import org.releaf.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String name) throws BeansException {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean;

        if (beanDefinition.isSingleton()) {
            bean = getSingleton(name, () -> {
                try {
                    return createBean(name, beanDefinition);
                } catch (BeansException e) {
                    destroySingleton(name);
                    throw e;
                }
            });
        } else {
            // 原型，直接创建无缓存
            bean = createBean(name, beanDefinition);
        }
        // 统一后处理
        return getObjectForBeanInstance(bean, name);
    }

    /**
     * 如果是FactoryBean，从FactoryBean#getObject中创建bean
     *
     * @param beanInstance
     * @param beanName
     * @return
     */
    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object =  beanInstance;
        if (beanInstance instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            try {
                if (factoryBean.isSingleton()) {
                    // singleton作用域bean，从缓存中获取
                    object = this.factoryBeanObjectCache.get(beanName);
                    if (object == null) {
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, object);
                    }
                } else {
                    // prototype作用域bean，新创建bean
                    object = factoryBean.getObject();
                }

            } catch (Exception e) {
                throw new BeansException("FactoryBean threw exception on object[" + beanName + "]", e);
            }
        }

        return object;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return ((T) getBean(name));
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        // 有则覆盖
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }

}
