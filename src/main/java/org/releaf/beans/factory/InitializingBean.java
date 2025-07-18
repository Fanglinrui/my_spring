package org.releaf.beans.factory;

public interface InitializingBean {

    /**
     * 初始化方法
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
