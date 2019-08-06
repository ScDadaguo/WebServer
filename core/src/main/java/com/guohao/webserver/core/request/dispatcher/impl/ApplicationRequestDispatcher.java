/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.request.dispatcher.impl;

import com.guohao.webserver.core.constant.CharsetProperties;
import com.guohao.webserver.core.exception.ResourceNotFoundException;
import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.request.dispatcher.RequestDispatcher;
import com.guohao.webserver.core.resource.ResourceHandler;
import com.guohao.webserver.core.response.Response;
import com.guohao.webserver.core.template.TemplateResolver;
import com.guohao.webserver.core.util.IOUtil;
import com.guohao.webserver.core.util.MimeTypeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @FileName: ApplicationRequestDispatcher.java
 * @Description: 请求转发器
 * @Author: guohao
 * @Date: 2019/8/6 12:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ApplicationRequestDispatcher implements RequestDispatcher {
        private String url;

        @Override
        public void forward(Request request, Response response) throws ServletException, IOException {
            if (ResourceHandler.class.getResource(url) == null) {
                throw new ResourceNotFoundException();
            }
            log.info("forward至 {} 页面",url);
            String body = TemplateResolver.resolve(new String(IOUtil.getBytesFromFile(url), CharsetProperties.UTF_8_CHARSET),request);
            response.setContentType(MimeTypeUtil.getTypes(url));
            response.setBody(body.getBytes(CharsetProperties.UTF_8_CHARSET));
        }

}
