package org.releaf.beans.factory;

import org.releaf.beans.BeansException;

public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
