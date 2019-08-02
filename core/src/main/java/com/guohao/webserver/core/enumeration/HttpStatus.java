/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.enumeration;

/**
 * @FileName: HttpStatus.java
 * @Description: HttpStatus.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 10:59
 */
public enum  HttpStatus {
    OK(200),NOT_FOUND(404),INTERNAL_SERVER_ERROR(500),BAD_REQUEST(400),MOVED_TEMPORARILY(302);
    private int code;
    HttpStatus(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
