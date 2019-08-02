/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.network.handler;

import com.guohao.webserver.core.context.ServletContext;
import com.guohao.webserver.core.exception.handler.ExceptionHandler;
import com.guohao.webserver.core.filter.Filter;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.resource.ResourceHandler;
import com.guohao.webserver.core.response.Response;
import com.guohao.webserver.core.servlet.Servlet;
import com.guohao.webserver.core.wrapper.SocketWrapper;

import java.util.List;

/**
 * @FileName: AbstractRequestHandler.java
 * @Description: AbstractRequestHandler.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:59
 */
public class AbstractRequestHandler {
    protected Request request;
    protected Response response;
    protected SocketWrapper socketWrapper;
    protected ServletContext servletContext;
    protected ExceptionHandler exceptionHandler;
    protected ResourceHandler resourceHandler;
    protected boolean isFinished;
    protected Servlet servlet;
    protected List<Filter> filters;
    private int filterIndex = 0;
}
