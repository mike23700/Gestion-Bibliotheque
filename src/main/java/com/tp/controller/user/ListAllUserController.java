package com.tp.controller.user;

import com.tp.dao.DAOFactory;
import com.tp.model.User;
import com.tp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listUser")
public class ListAllUserController extends HttpServlet{

    private UserService userService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.userService = new UserService(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> userList = new ArrayList<>();
        String type = request.getParameter("type");

        if ("admins".equals(type)) {
            userList = userService.getAllAdmins();
        }else if ("members".equals("type")){
            userList = userService.getAllMembers();
        }else {
            userList = userService.getAllUsers();
        }

        request.setAttribute("userList", userList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
    }
}



