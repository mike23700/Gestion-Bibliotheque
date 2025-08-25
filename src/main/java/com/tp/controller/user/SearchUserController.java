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
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/admin/searchResult.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
            String type = request.getParameter("type");
            String input = request.getParameter("input");

            List<User> result = new ArrayList<>();

            try {
                if ("user_id".equals(type)) {
                    User user = userService.findUserById(input);
                    if (user != null) {
                        result.add(user);
                    }
                } else if ("name".equals(type.trim())) {
                    result = userService.findUserByName(input);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("result", result);
            this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/admin/searchResult.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }
}