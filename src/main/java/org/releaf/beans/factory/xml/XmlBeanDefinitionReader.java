package org.releaf.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.releaf.beans.BeansException;
import org.releaf.beans.PropertyValue;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.config.BeanReference;
import org.releaf.beans.factory.support.AbstractBeanDefinitionReader;
import org.releaf.beans.factory.support.BeanDefinitionRegistry;
import org.releaf.core.io.Resource;
import org.releaf.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";
    // init-and-destroy
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            try {
                // 通过 xml 文件load的具体逻辑，封装为一个函数
                doLoadBeanDefinitions(inputStream);
            } finally {
                inputStream.close();

            }

        } catch ( IOException | DocumentException ex){
            throw new BeansException("IOException parsing XML document from " + resource, ex);
        }

    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        Element beans = document.getRootElement();
        List<Element> beanList = beans.elements(BEAN_ELEMENT);
        for(Element bean : beanList){
            String beanId = bean.attributeValue(ID_ATTRIBUTE);
            String beanName = bean.attributeValue(NAME_ATTRIBUTE);
            String className = bean.attributeValue(CLASS_ATTRIBUTE);
            // init and destroy
            String initMethodName = bean.attributeValue(INIT_METHOD_ATTRIBUTE);
            String destroyMethodName = bean.attributeValue(DESTROY_METHOD_ATTRIBUTE);


            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeansException("Cannot find class [" + className +"]");
            }
            //id优先于name
            beanName = StrUtil.isEmpty(beanId) ? beanId : beanName;
            if(StrUtil.isEmpty(beanName)){
                //如果id和name都为空，将类名的第一个字母转为小写后作为bean的名称
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            List<Element> propertyList = beans.elements(PROPERTY_ELEMENT);
            for(Element property : propertyList){
                // property tag
                String propertyNameAttribute = property.attributeValue(NAME_ATTRIBUTE);
                String propertyValueAttribute = property.attributeValue(VALUE_ATTRIBUTE);
                String propertyRefAttribute = property.attributeValue(REF_ATTRIBUTE);

                if(StrUtil.isEmpty(propertyNameAttribute)){
                    throw new BeansException("name attribute is empty");
                }

                Object value = propertyValueAttribute;
                if(StrUtil.isNotEmpty(propertyRefAttribute)){
                    value = new BeanReference(propertyNameAttribute);
                }
                PropertyValue propertyValue = new PropertyValue(propertyNameAttribute, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName)){
                // 不能重名
                throw new BeansException("Duplicate beanName [" + beanName + "]");
            }
            // 注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }

    }
}
