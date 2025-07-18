package org.releaf.context.support;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.ConfigurableListableBeanFactory;
import org.releaf.beans.factory.config.BeanFactoryPostProcessor;
import org.releaf.beans.factory.config.BeanPostProcessor;
import org.releaf.context.ConfigurableApplicationContext;
import org.releaf.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象应用上下文
 *
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException{
        //创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //添加ApplicationContextAwareProcessor，继承自ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //BeanPostProcessor需要提前与其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);

        //提前实例化单例bean
        beanFactory.preInstantiateSingletons();

    }

    /**
     * 创建BeanFactory，并加载BeanDefinition
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;


    /**
     * 在bean实例化之前，执行BeanFactoryPostProcessor
     *
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册BeanPostProcessor
     *
     * @param beanFactory
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException{
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    // <T> 表明定义了T是一个泛型
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException{
        return getBeanFactory().getBeansOfType(type);
    }

    public Object getBean(String name) throws  BeansException{
        return getBeanFactory().getBean(name);
    }

    public String[] getBeanDefinitionNames(){ return getBeanFactory().getBeanDefinitionNames(); }

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    public void close(){ doClose(); }

    public void registerShutdownHook(){
        Thread shutdownHook = new Thread(){
            public void run(){
                doClose();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    protected void doClose(){
        destroyBeans();
    }

    protected void destroyBeans(){
        getBeanFactory().destroySingletons();
    }
}
