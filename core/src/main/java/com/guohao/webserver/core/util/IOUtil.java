/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @FileName: IOUtil.java
 * @Description: IOUtil.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:32
 */
@Slf4j
public class IOUtil {
    public static byte[] getBytesFromFile(String fileName) throws IOException {
        InputStream in = IOUtil.class.getResourceAsStream(fileName);
        if (in == null) {
            log.info("Not Found File:{}",fileName);
            throw new FileNotFoundException();
        }
        log.info("正在读取文件:{}",fileName);
        return getBytesFromStream(in);
    }

    public static byte[] getBytesFromStream(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = in.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        in.close();
        return outStream.toByteArray();
    }
}
