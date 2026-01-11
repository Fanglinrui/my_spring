package org.releaf.context.annotation;

import cn.hutool.core.util.ClassUtil;
import org.releaf.beans.factory.config.BeanDefinition;
import org.releaf.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        // 扫描有org.releaf.stereotype.Component注解的类 (正常应该把releaf改为springframework)
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            candidates.add(beanDefinition);
        }
        return candidates;
    }
}
