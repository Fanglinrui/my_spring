package org.releaf.beans.factory.support;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.DisposableBean;
import org.releaf.beans.factory.ObjectFactory;
import org.releaf.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<String, Object>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) throws BeansException {
        return singletonObjects.get(beanName);
    }

    @Override
    public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject == null && singletonFactory != null) {
            singletonObject = singletonFactory.getObject();
            if (singletonObject != null) {
                addSingleton(beanName, singletonObject);
            }
        }
        return singletonObject;
    }

    public void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons(){
        Set<String> beanNames = disposableBeanMap.keySet();
        for(String beanName : beanNames){
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try{
                disposableBean.destroy();
            }catch(Exception e){
                throw new BeansException("Destroy method on bean named'" + beanName + "'threw an exception", e);
            }
        }
    }

    public void destroySingleton(String beanName){
        DisposableBean disposableBean = disposableBeanMap.remove(beanName);
        try{
            disposableBean.destroy();
        }catch(Exception e){
            throw new BeansException("Destroy method on bean named'" + beanName + "threw an exception", e);
        }
    }
}
