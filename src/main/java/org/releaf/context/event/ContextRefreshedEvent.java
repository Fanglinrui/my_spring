package org.releaf.context.event;

import org.releaf.context.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
