package com.guohao.webserver.core.exception;

import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.exception.base.ServletException;

/**
 * Created by guohao on 2019/7/21.
 * 请求数据不合法
 */
public class RequestInvalidException extends ServletException {
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;
    public RequestInvalidException() {
        super(status);
    }
}
