/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core;

import com.guohao.webserver.core.context.WebApplication;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @FileName: IdleSessionCleaner.java
 * @Description: IdleSessionCleaner.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 14:37
 */
@Slf4j
public class IdleSessionCleaner implements Runnable {
    private ScheduledExecutorService executor;

    public IdleSessionCleaner() {
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "IdleSessionCleaner");
            }
        };
        this.executor = Executors.newSingleThreadScheduledExecutor(threadFactory);
    }

    public void start() {
        executor.scheduleAtFixedRate(this, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        log.info("开始扫描过期session...");
        WebApplication.getServletContext().cleanIdleSessions();
        log.info("扫描结束...");
    }
}
