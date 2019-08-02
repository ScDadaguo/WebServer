///*
// * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
// */
//
//package com.guohao.webserver.core.network.dispatcher;
//
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * @FileName: AbstractDispatcher.java
// * @Description: AbstractDispatcher.java类说明
// * @Author: guohao
// * @Date: 2019/8/2 10:48
// */
//public abstract class AbstractDispatcher {
//    protected ThreadPoolExecutor pool;
//
//    /**
//     * 关闭
//     */
//    public void shutdown() {
//        pool.shutdown();
////        servletContext.destroy();
//    }
//
//    /**
//     * 分发请求
////     * @param socketWrapper
//     */
////    public abstract void doDispatch(SocketWrapper socketWrapper);
//
//    public AbstractDispatcher() {
////        this.servletContext = WebApplication.getServletContext();
////        this.exceptionHandler = new ExceptionHandler();
////        this.resourceHandler = new ResourceHandler(exceptionHandler);
//        ThreadFactory threadFactory = new ThreadFactory() {
//            private int count;
//
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "Worker Pool-" + count++);
//            }
//        };
//        this.pool = new ThreadPoolExecutor(100, 100, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
//    }
//}
