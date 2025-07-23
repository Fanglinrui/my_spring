package org.releaf.context;

import java.util.EventListener;

public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    public void onApplicationEvent(E event);
}
