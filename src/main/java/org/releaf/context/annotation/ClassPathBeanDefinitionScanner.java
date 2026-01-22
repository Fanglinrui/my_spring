package org.releaf.context.annotation;

import cn.hutool.core.util.StrUtil;
import org.releaf.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.beans.factory.support.BeanDefinitionRegistry;
import org.releaf.stereotype.Component;

import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "org.releaf.context.annotation.internalAutowiredAnnotationProcessor";

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    // 表示可以传数组、0个或多个String，使用时就当作数组就可以了
    public void doScan(String... basePackages){
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                // 解析作用域
                String scope = resolveBeanScope(candidate);
                if (StrUtil.isNotEmpty(scope)) {
                    candidate.setScope(scope);
                }
                // 生成bean的名称
                String beanName = determineBeanName(candidate);
                // 注册BeanDefinition
                registry.registerBeanDefinition(beanName, candidate);

            }
        }

        // 注册处理@Autowired和@Value注解的BeanPostProcessor
        registry.registerBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));

    }

    /**
     * 解析作用域
     *
     * @param beanDefinition
     * @return
     */
    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            return scope.value();
        }

        return StrUtil.EMPTY;
    }

    /**
     * 生成bean的名称
     *
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
