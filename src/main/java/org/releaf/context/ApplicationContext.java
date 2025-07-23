package org.releaf.context;

import org.releaf.beans.factory.HierarchicalBeanFactory;
import org.releaf.beans.factory.ListableBeanFactory;
import org.releaf.core.io.ResourceLoader;


/**
 * 应用上下文
 *
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
