package org.releaf.beans.factory.support;

//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
import org.releaf.beans.BeansException;
import org.releaf.beans.factory.config.BeanDefinition;

public class CglibSubclassintInstantiationStrategy implements InstantiationStrategy {
    /**
     * 使用 CGLIB 动态生成子类
     *
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(beanDefinition.getBeanClass());
//        enhancer.setCallback((MethodInterceptor) (obj, method, argsTemp, proxy) -> proxy.invokeSuper(obj,argsTemp));
//        return enhancer.create();
        throw new UnsupportedOperationException("CGLIB instantiation strategy is not supported");
    }
}
