package com.tp.controller.user;

import com.tp.dao.DAOFactory;
import com.tp.model.User;
import com.tp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchUser")
public class SearchUserController extends HttpServlet{

    private UserService userService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.userService = new UserService(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String type = request.getParameter("type");
       String input = request.getParameter("input");

       List<User> result = new ArrayList<>();

        try {
            if ("user_id".equals(type)) {
                User user = userService.findUserById(input);
                if (user != null) {
                    result.add(user);
                }
            } else if ("name ".equals(type)) {
                result = userService.findUserByName(input);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("result", result);
        this.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
    }
}
