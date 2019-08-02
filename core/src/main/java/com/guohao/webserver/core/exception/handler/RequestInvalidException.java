/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.exception.handler;

import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.exception.base.ServletException;

/**
 * @FileName: RequestInvalidException.java
 * @Description: 请求数据不合法
 * @Author: guohao
 * @Date: 2019/8/2 11:30
 */
public class RequestInvalidException extends ServletException {

    private static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public RequestInvalidException() {
        super(status);
    }
}
