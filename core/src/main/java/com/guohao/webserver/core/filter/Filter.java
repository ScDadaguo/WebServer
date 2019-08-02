/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.filter;

import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;

/**
 * @FileName: Filter.java
 * @Description: Filter.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:12
 */
public interface Filter {

    /**
     * 过滤器初始化
     */
    void init();

    /**
     * 过滤
     * @param request
     * @param response
     * @param filterChain
     */
    void doFilter(Request request, Response response, FilterChain filterChain) ;

    /**
     * 过滤器销毁
     */
    void destroy();
}
