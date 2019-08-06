package com.guohao.webserver.sample.web.servlet;

import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;
import com.guohao.webserver.core.servlet.impl.HttpServlet;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by guohao on 2019/7/21.
 */
@Slf4j
public class LogoutServlet extends HttpServlet {
    
    @Override
    public void doGet(Request request, Response response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/logout.html").forward(request,response);  
    }

    @Override
    public void doPost(Request request, Response response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().invalidate();
        response.sendRedirect("/login");
    }
}
