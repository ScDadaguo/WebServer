/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.wrapper;

import java.io.IOException;

/**
 * @FileName: SocketWrapper.java
 * @Description: SocketWrapper.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:20
 */
public interface SocketWrapper {
    void close() throws IOException;
}
