/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.constant;

/**
 * @FileName: ContextConstant.java
 * @Description: ContextConstant.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:36
 */
public class ContextConstant {
    public static final String ERROR_PAGE = "/errors/%s.html";
    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=utf-8";
    public static final String DEFAULT_SERVLET_ALIAS = "DefaultServlet";
    /**
     * 300s 5min 过期
     */
    public static final int DEFAULT_SESSION_EXPIRE_TIME = 300;
}
