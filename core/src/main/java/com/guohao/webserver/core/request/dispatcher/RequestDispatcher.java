/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.request.dispatcher;

import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;

import java.io.IOException;

/**
 * @FileName: RequestDispatcher.java
 * @Description: RequestDispatcher.java类说明
 * @Author: guohao
 * @Date: 2019/8/6 12:07
 */
public interface RequestDispatcher {
    void forward(Request request, Response response) throws ServletException, IOException;
}
