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
 * @author guohao
 * @date 2019/5/3
 */
@Slf4j
public class UserEditServlet extends HttpServlet {
    private UserService userService;

    public UserEditServlet() {
        userService = UserService.getInstance();
    }

    
    @Override
    public void doGet(Request request, Response response) throws ServletException, IOException {
        User user = userService.findByUsername((String) request.getSession().getAttribute("username"));
        request.setAttribute("user",user);
        request.getRequestDispatcher("/views/userEdit.html").forward(request,response);
    }

    @Override
    public void doPost(Request request, Response response) throws ServletException, IOException {
        log.info("{}",request.getParams());
        User user = new User();
        user.setUsername((String) request.getSession(false).getAttribute("username"));
        user.setRealName(request.getParameter("realName"));
        user.setAge(Integer.valueOf(request.getParameter("age")));
        userService.update(user);
        
        request.setAttribute("user",user);
        request.getRequestDispatcher("/views/user.html").forward(request, response);
    }
}
