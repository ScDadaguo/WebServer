package com.guohao.webserver.core.exception.base;

import com.guohao.webserver.core.enumeration.HttpStatus;
import lombok.Getter;

/**
 * Created by SinjinSong on 2017/7/20.
 * 根异常
 */
@Getter
public class ServletException extends Exception {
    private HttpStatus status;
    public ServletException(HttpStatus status){
        this.status = status;
    }
}
