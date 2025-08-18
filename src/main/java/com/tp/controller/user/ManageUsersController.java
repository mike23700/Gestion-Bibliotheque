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
import java.util.List;
import java.time.LocalDateTime;

@WebServlet("/manageUsers")
public class ManageUsersController extends HttpServlet {

    private UserService userService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.userService = new UserService(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
            List<User> userList = userService.getAllUsers();
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String action = request.getParameter("action");
        String userId = request.getParameter("user_id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        boolean success = false;

        if ("add".equals(action)) {
            User newUser = new User(null, name, surname, null, null, LocalDateTime.now());
            success = userService.addUser(newUser);
        } else if ("delete".equals(action)) {
            success = userService.deleteUser(userId);
        }

        if (success) {
            request.getSession().setAttribute("message", "Opération réussie.");
        } else {
            request.getSession().setAttribute("error", "Échec de l'opération.");
        }

        response.sendRedirect(request.getContextPath() + "/manageUsers");
    }
}