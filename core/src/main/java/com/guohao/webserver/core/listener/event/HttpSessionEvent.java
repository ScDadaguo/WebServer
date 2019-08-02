
package com.guohao.webserver.core.listener.event;


import com.guohao.webserver.core.session.HttpSession;

/**
 * @FileName: HttpSessionEvent.java
 * @Description: session相关的事件
 * @Author: guohao
 * @Date: 2019/8/2 14:42
 */
public class HttpSessionEvent extends java.util.EventObject {

    private static final long serialVersionUID = -7622791603672342895L;


    public HttpSessionEvent(HttpSession source) {
        super(source);
    }

    public HttpSession getSession () {
        return (HttpSession) super.getSource();
    }
}

