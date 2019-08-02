package com.guohao.webserver.core.context.holder;

import com.guohao.webserver.core.servlet.Servlet;
import lombok.Data;

/**
 * @author guohao
 * @date 2019/7/31
 */
@Data
public class ServletHolder {
    private Servlet servlet;
    private String servletClass;

    public ServletHolder(String servletClass) {
        this.servletClass = servletClass;
    }
}
