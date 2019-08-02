/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.context;

/**
 * @FileName: WebApplication.java
 * @Description: 静态持有servletContext，保持servletContext能在项目启动时就被初始化
 * @Author: guohao
 * @Date: 2019/8/2 14:35
 */
public class WebApplication {
    private static ServletContext servletContext;

    static {
        try {
            servletContext = new ServletContext();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }
}