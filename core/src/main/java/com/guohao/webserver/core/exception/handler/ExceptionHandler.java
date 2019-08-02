/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.exception.handler;

import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.response.Header;
import com.guohao.webserver.core.response.Response;
import com.guohao.webserver.core.util.IOUtil;
import com.guohao.webserver.core.wrapper.SocketWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.guohao.webserver.core.constant.ContextConstant.ERROR_PAGE;

/**
 * @FileName: ExceptionHandler.java
 * @Description: ExceptionHandler.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 10:56
 */
@Slf4j
public class ExceptionHandler {
    public void handle(ServletException e, Response response, SocketWrapper socketWrapper) {
        try {
            if (e instanceof RequestInvalidException) {
                log.info("请求无法读取，丢弃");
                socketWrapper.close();
            } else {
                log.info("抛出异常:{}", e.getClass().getName());
                e.printStackTrace();
                response.addHeader(new Header("Connection", "close"));
                response.setStatus(e.getStatus());
                response.setBody(IOUtil.getBytesFromFile(
                        String.format(ERROR_PAGE, String.valueOf(e.getStatus().getCode()))));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
