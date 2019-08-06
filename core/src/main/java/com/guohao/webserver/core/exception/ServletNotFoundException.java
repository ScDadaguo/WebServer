package com.guohao.webserver.core.exception;

import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.exception.base.ServletException;

/**
 * Created by guohao on 2019/7/21.
 * 未找到对应的Servlet（web.xml配置错误）
 */
public class ServletNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public ServletNotFoundException() {
        super(status);
    }
}
