/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.exception.base;

import com.guohao.webserver.core.enumeration.HttpStatus;
import lombok.Getter;

/**
 * @FileName: ServletException.java
 * @Description: ServletException.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 10:58
 */
@Getter
public class ServletException extends Exception {
    private HttpStatus status;
    public ServletException(HttpStatus status){
        this.status = status;
    }
}
