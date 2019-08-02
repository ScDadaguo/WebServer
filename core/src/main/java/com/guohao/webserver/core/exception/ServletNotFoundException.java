/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.exception;

import com.guohao.webserver.core.enumeration.HttpStatus;
import com.guohao.webserver.core.exception.base.ServletException;

/**
 * @FileName: ServletNotFoundException.java
 * @Description: ServletNotFoundException.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 14:27
 */
public class ServletNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public ServletNotFoundException() {
        super(status);
    }
}
