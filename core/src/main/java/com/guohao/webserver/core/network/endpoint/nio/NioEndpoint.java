/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.network.endpoint.nio;

import com.guohao.webserver.core.network.connector.nio.NioPoller;
import com.guohao.webserver.core.network.endpoint.Endpoint;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @FileName: NioEndpoint.java
 * @Description: NioEndpoint.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 10:36
 */
public class NioEndpoint extends Endpoint {

    private ServerSocketChannel server;

    private List<NioPoller> nioPollers;
    /**
     * poller轮询器
     */
    private AtomicInteger pollerRotater = new AtomicInteger(0);
    @Override
    public void start(int port) {

    }

    @Override
    public void close() {

    }

    private void initServerSocket(int port) throws IOException {
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(port));
        server.configureBlocking(true);
    }


}
