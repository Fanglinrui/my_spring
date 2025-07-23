package org.releaf.context.event;

import org.releaf.context.ApplicationContext;
import org.releaf.context.ApplicationEvent;

public class ContextClosedEvent extends ApplicationContextEvent {

    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
