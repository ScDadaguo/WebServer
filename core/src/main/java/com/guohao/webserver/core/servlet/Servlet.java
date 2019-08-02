/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.servlet;

import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;

import java.io.IOException;

/**
 * @FileName: Servlet.java
 * @Description: Servlet.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 12:00
 */
public interface Servlet {
    void init();

    void destroy();

    void service(Request request, Response response) throws ServletException, IOException;
}
