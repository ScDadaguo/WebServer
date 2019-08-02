/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.listener;

import com.guohao.webserver.core.listener.event.ServletContextEvent;

import java.util.EventListener;

/**
 * @FileName: ServletContextListener.java
 * @Description: 应用层面上的监听器
 * @Author: guohao
 * @Date: 2019/8/2 14:41
 */
public interface ServletContextListener extends EventListener {
    /**
     * 应用启动
     * @param sce
     */
    void contextInitialized(ServletContextEvent sce);

    /**
     * 应用关闭
     * @param sce
     */
    void contextDestroyed(ServletContextEvent sce);
}

