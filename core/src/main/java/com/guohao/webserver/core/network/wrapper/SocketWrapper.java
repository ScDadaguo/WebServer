/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.network.wrapper;

import java.io.IOException;

/**
 * @FileName: SocketWrapper2.java
 * @Description: SocketWrapper2.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 15:12
 */
public interface SocketWrapper {
    void close() throws IOException;
}

