/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.filter;

import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;

/**
 * @FileName: FilterChain.java
 * @Description: 拦截器链
 * @Author: guohao
 * @Date: 2019/8/2 11:14
 */
public interface FilterChain {
    /**
     * 当前filter放行，由后续的filter继续进行过滤
     * @param request
     * @param response
     */
    void doFilter(Request request, Response response) ;
}
