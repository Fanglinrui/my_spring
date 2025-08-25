package org.releaf.test.common;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.config.BeanPostProcessor;
import org.releaf.test.bean.Car;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor.postProcessBeforeInitialization");
        //换兰博基尼
        if ("car".equals(beanName)) {
            ((Car) bean).setBrand("lamborghini");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor.postProcessAfterInitialization");
        return bean;
    }
}
