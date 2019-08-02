package com.guohao.webserver.core.util;

import java.util.UUID;

/**
 * @FileName: UUIDUtil.java
 * @Description: UUIDUtil.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 14:34
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
