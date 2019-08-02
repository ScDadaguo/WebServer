/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.exception;

import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.exception.base.ServletException;

/**
 * @FileName: FilterNotFoundException.java
 * @Description: 未找到对应的Filter（web.xml配置错误）
 * @Author: guohao
 * @Date: 2019/8/2 14:31
 */
public class FilterNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public FilterNotFoundException() {
        super(status);
    }
}
