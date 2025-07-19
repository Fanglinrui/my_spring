package org.releaf.test.ioc.service;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.BeanFactory;
import org.releaf.beans.factory.BeanFactoryAware;
import org.releaf.context.ApplicationContext;
import org.releaf.context.ApplicationContextAware;

public class HelloService implements ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public String sayHello(){
        System.out.println("Hello");
        return "hello";
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
