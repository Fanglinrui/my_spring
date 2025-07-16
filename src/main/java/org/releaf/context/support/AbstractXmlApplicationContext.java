package org.releaf.context.support;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.support.DefaultListableBeanFactory;
import org.releaf.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if(configLocations != null){
            reader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
