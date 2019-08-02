/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.response;

import com.guohao.webserver.core.cookie.Cookie;
import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.network.handler.AbstractRequestHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.guohao.webserver.core.constant.ContextConstant.DEFAULT_CONTENT_TYPE;

/**
 * @FileName: Response.java
 * @Description: Response.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:05
 */

@Slf4j
public class Response {
    private StringBuilder headerAppender;
    private List<Cookie> cookies;
    private List<Header> headers;
    private HttpStatus status = HttpStatus.OK;
    private String contentType = DEFAULT_CONTENT_TYPE;
    private byte[] body = new byte[0];
    private AbstractRequestHandler requestHandler;

    public void addCookie(Cookie jsessionid) {
    }
}
