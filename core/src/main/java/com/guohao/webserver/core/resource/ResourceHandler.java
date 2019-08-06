/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.resource;

import com.guohao.webserver.core.constant.CharsetProperties;
import com.guohao.webserver.core.exception.RequestParseException;
import com.guohao.webserver.core.exception.ResourceNotFoundException;
import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.exception.handler.ExceptionHandler;
import com.guohao.webserver.core.network.wrapper.nio.NioSocketWrapper;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;
import com.guohao.webserver.core.template.TemplateResolver;
import com.guohao.webserver.core.util.IOUtil;
import com.guohao.webserver.core.util.MimeTypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @FileName: ResourceHandler.java
 * @Description: ResourceHandler.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 10:55
 */
@Slf4j
public class ResourceHandler {
    private ExceptionHandler exceptionHandler;

    public ResourceHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void handle(Request request, Response response, NioSocketWrapper socketWrapper) {
        String url = request.getUrl();
        try {
            if (ResourceHandler.class.getResource(url) == null) {
                log.info("找不到该资源:{}", url);
                throw new ResourceNotFoundException();
            }
            byte[] body = IOUtil.getBytesFromFile(url);
            if (url.endsWith(".html")) {
                body = TemplateResolver
                        .resolve(new String(body, CharsetProperties.UTF_8_CHARSET), request)
                        .getBytes(CharsetProperties.UTF_8_CHARSET);
            }
            response.setContentType(MimeTypeUtil.getTypes(url));
            response.setBody(body);
        } catch (IOException e) {
            exceptionHandler.handle(new RequestParseException(), response, socketWrapper);
        } catch (ServletException e) {
            exceptionHandler.handle(e, response, socketWrapper);
        }
    }
}