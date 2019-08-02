/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.network.wrapper.nio;

import com.guohao.webserver.core.network.connector.nio.NioPoller;
import com.guohao.webserver.core.network.endpoint.nio.NioEndpoint;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @FileName: NioSocketWrapper.java
 * @Description: NioSocketWrapper.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 15:11
 */
public class NioSocketWrapper  {
    private final NioEndpoint server;
    private final SocketChannel socketChannel;
    private final NioPoller nioPoller;
    private final boolean isNewSocket;
    private volatile long waitBegin;
    private volatile boolean isWorking;

    public NioSocketWrapper(NioEndpoint server, SocketChannel socketChannel, NioPoller nioPoller, boolean isNewSocket) {
        this.server = server;
        this.socketChannel = socketChannel;
        this.nioPoller = nioPoller;
        this.isNewSocket = isNewSocket;
        this.isWorking = false;
    }

    public void close() throws IOException {
        socketChannel.keyFor(nioPoller.getSelector()).cancel();
        socketChannel.close();
    }



    @Override
    public String toString() {
        return socketChannel.toString();
    }
}