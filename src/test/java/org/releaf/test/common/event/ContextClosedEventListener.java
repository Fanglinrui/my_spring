package org.releaf.test.common.event;

import org.releaf.context.ApplicationListener;
import org.releaf.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
