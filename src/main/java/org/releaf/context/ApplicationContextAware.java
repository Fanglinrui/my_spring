package org.releaf.context;

import org.releaf.beans.BeansException;
import org.releaf.beans.factory.Aware;

/**
 * 实现该接口，能感知所属的ApplicationContext
 *
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
