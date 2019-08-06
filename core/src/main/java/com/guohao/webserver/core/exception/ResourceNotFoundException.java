package com.guohao.webserver.core.exception;

import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.exception.base.ServletException;

/**
 * Created by guohao on 2019/7/21.
 * 静态资源未找到
 */
public class ResourceNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public ResourceNotFoundException() {
        super(status);
    }
}
