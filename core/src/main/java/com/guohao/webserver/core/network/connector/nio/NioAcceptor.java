/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.network.connector.nio;

import com.guohao.webserver.core.network.endpoint.nio.NioEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @FileName: NioAcceptor.java @Description: NioAcceptor.java类说明 @Author: guohao @Date: 2019/8/2
 * 16:45
 */
@Slf4j
public class NioAcceptor implements Runnable {
   private NioEndpoint nioEndpoint;

  public NioAcceptor(NioEndpoint nioEndpoint) {
    this.nioEndpoint = nioEndpoint;
  }

  @Override
  public void run() {
    log.info("{} 开始监听", Thread.currentThread().getName());
    while (nioEndpoint.isRunning()) {
      SocketChannel client;
      try {
        client = nioEndpoint.accept();
        if (client == null) {
          continue;
        }
        client.configureBlocking(false);
        log.info("Acceptor接收到连接请求 {}", client);
        nioEndpoint.registerToPoller(client);
        log.info("socketWrapper:{}", client);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
