package com.guohao.webserver.core.servlet.impl;

import com.guohao.webserver.core.enumeration.RequestMethod;
import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;
import com.guohao.webserver.core.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by guohao on 2019/7/21.
 * 根Servlet，实现了不同HTTP方法的路由
 */
@Slf4j
public abstract class HttpServlet implements Servlet {

    @Override
    public void init() {
        
    }

    @Override
    public void destroy() {

    }

    @Override
    public void service(Request request, Response response) throws ServletException, IOException {
        if (request.getMethod() == RequestMethod.GET) {
            doGet(request, response);
        } else if (request.getMethod() == RequestMethod.POST) {
            doPost(request, response);
        } else if (request.getMethod() == RequestMethod.PUT) {
            doPut(request, response);
        } else if (request.getMethod() == RequestMethod.DELETE) {
            doDelete(request, response);
        }
    }

    public void doGet(Request request, Response response) throws ServletException, IOException {
    }

    public void doPost(Request request, Response response) throws ServletException, IOException {
    }

    public void doPut(Request request, Response response) throws ServletException, IOException {
    }

    public void doDelete(Request request, Response response) throws ServletException, IOException {
    }


}
