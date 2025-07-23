package org.releaf.context.event;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.BeanFactory;
import org.releaf.context.ApplicationEvent;
import org.releaf.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportsEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }
        }

    }

    /**
     * 监听器是否对该事件感兴趣，如果event是ApplicationEvent或其子类，则返回true
     *
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        // 获取applicationListener类实现的第一个接口类型（通常就是 ApplicationListener<T>）
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        // 提取出 ApplicationListener<T> 中的 T，即监听器所监听的事件类型。
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);

        } catch (ClassNotFoundException e) {
            throw new BeansException("Wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
