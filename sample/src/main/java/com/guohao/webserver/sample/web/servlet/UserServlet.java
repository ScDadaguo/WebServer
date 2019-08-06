package com.guohao.webserver.sample.web.servlet;

import com.guohao.webserver.core.exception.base.ServletException;
import com.guohao.webserver.core.request.Request;
import com.guohao.webserver.core.response.Response;
import com.guohao.webserver.core.servlet.impl.HttpServlet;
import com.guohao.webserver.sample.domain.User;
import com.guohao.webserver.sample.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by SinjinSong on 2017/7/21.
 */
@Slf4j
public class UserServlet extends HttpServlet {
    private UserService userService;

    public UserServlet() {
        userService = UserService.getInstance();
    }
    
    @Override
    public void doGet(Request request, Response response) throws ServletException, IOException {
        User user = userService.findByUsername((String) request.getSession().getAttribute("username"));
        request.setAttribute("user",user);
        request.getRequestDispatcher("/views/user.html").forward(request, response);
    }
}
