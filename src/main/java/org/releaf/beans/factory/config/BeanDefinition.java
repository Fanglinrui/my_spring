package org.releaf.beans.factory.config;

import org.releaf.beans.PropertyValues;

/**
 *  * BeanDefinition实例保存bean的信息，包括class类型、方法构造参数、是否为单例等，此处简化只包含class类型，以及bean属性
 */
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    // init一节新增
    private String initMethodName;
    private String destroyMethodName;

    public BeanDefinition(Class beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() { return beanClass; }

    public void setBeanClass(Class beanClass) { this.beanClass = beanClass; }

    public PropertyValues getPropertyValues() { return propertyValues; }

    public void setPropertyValues(PropertyValues propertyValues) { this.propertyValues = propertyValues; }

    public String getInitMethodName() { return initMethodName; }
    public void setInitMethodName(String initMethodName) { this.initMethodName = initMethodName; }

    public String getDestroyMethodName() { return destroyMethodName; }
    public void setDestroyMethodName(String destroyMethodName) { this.destroyMethodName = destroyMethodName; }

}
