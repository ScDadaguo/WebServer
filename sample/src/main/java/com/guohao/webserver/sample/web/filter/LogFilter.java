package com.guohao.webserver.sample.web.filter;

import com.guohao.webserver.core.filter.Filter;
import com.guohao.webserver.core.filter.FilterChain;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author guohao
 * @date 2018/5/3
 */
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init() {
        log.info("LogFilter init...");
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        log.info("{} before accessed, method is {}", request.getUrl(), request.getMethod());
        filterChain.doFilter(request, response);
        log.info("{} after accessed, method is {}", request.getUrl(), request.getMethod());
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy...");
    }
}
