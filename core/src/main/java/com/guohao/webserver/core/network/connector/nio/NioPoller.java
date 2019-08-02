/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.network.connector.nio;

import com.guohao.webserver.core.network.endpoint.nio.NioEndpoint;
import com.guohao.webserver.core.network.wrapper.nio.NioSocketWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;

/**
 * @FileName: NioPoller.java
 * @Description: NioPoller.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 15:09
 */
@Slf4j
public class NioPoller implements Runnable{
    private NioEndpoint nioEndpoint;
    private Selector selector;
    private Queue<PollerEvent> events;
    private String pollerName;
    private Map<SocketChannel, NioSocketWrapper> sockets;
    @Override
    public void run() {

    }
    public Selector getSelector() {
        return selector;
    }



    @Data
    @AllArgsConstructor
    private static class PollerEvent implements Runnable {
        private NioSocketWrapper wrapper;

        @Override
        public void run() {
            log.info("将SocketChannel的读事件注册到Poller的selector中");
            try {
                if (wrapper.getSocketChannel().isOpen()) {
                    wrapper.getSocketChannel().register(wrapper.getNioPoller().getSelector(), SelectionKey.OP_READ, wrapper);
                } else {
                    log.error("socket已经被关闭，无法注册到Poller", wrapper.getSocketChannel());
                }
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }
    }
}
