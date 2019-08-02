/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.listener.event;

import com.guohao.webserver.core.context.ServletContext;

/**
 * @FileName: ServletContextEvent.java
 * @Description: servletContext相关的事件
 * @Author: guohao
 * @Date: 2019/8/2 14:42
 */
public class ServletContextEvent extends java.util.EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ServletContextEvent(Object source) {
        super(source);
    }

    public ServletContext getServletContext () {
        return (ServletContext) super.getSource();
    }


}
