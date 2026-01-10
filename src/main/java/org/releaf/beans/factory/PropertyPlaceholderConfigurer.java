package org.releaf.beans.factory;

import org.releaf.beans.BeansException;
import org.releaf.beans.PropertyValue;
import org.releaf.beans.PropertyValues;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.config.BeanFactoryPostProcessor;
import org.releaf.core.io.DefaultResourceLoader;
import org.releaf.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String PLACEHOLDER_PREFIX = "${";

    public static final String PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        // 加载属性配置文件
        Properties properties = loadProperties();

        // 属性值替换占位符
        processProperties(beanFactory, properties);
    }

    /**
     * 加载属性配置文件
     *
     * @return
     */
    private Properties loadProperties() {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            return properties;
        } catch (IOException e) {
            throw new BeansException("Can't load properties from " + location, e);
        }
    }

    private void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            resolvePropertyValue(beanDefinition, properties);
        }
    }

    private void resolvePropertyValue(BeanDefinition beanDefinition, Properties properties) {
        PropertyValues  propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof String) {

                // 仅支持一个占位符，比如${jdbc.url}?useSSL=false
                String strVal =  (String) value;
                StringBuilder buf = new StringBuilder(strVal);
                int startIdx = strVal.indexOf(PLACEHOLDER_PREFIX);
                int endIdx = strVal.indexOf(PLACEHOLDER_SUFFIX);
                if (startIdx != -1 && endIdx != -1 && startIdx < endIdx) {
                    String propKey = strVal.substring(startIdx + 2, endIdx);
                    String propVal = properties.getProperty(propKey);
                    buf.replace(startIdx, endIdx + 1, propVal);
                    // 同名的 pv 对，后来者会覆盖前面的
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buf.toString()));
                }
            }
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
