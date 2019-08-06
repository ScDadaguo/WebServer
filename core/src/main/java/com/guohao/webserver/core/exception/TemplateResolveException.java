package com.guohao.webserver.core.exception;

import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.exception.base.ServletException;

/**
 * Created by guohao on 2019/7/21.
 * 模板引擎解析错误（html文件编写错误）
 */
public class TemplateResolveException extends ServletException {
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public TemplateResolveException() {
        super(status);
    }
}
   
