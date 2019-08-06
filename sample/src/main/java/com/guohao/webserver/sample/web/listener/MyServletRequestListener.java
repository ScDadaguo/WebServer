package com.guohao.webserver.sample.web.listener;

import com.guohao.webserver.core.listener.ServletRequestListener;
import com.guohao.webserver.core.listener.event.ServletRequestEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author guohao
 * @date 2018/5/3
 */
@Slf4j
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("request destroy...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("request init...");
    }
}
