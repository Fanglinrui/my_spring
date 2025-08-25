package org.releaf.test.common.event;

import org.releaf.context.ApplicationContext;
import org.releaf.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
