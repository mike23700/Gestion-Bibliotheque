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

@WebServlet("/changePassword")
public class ChangePasswordController extends HttpServlet {

    private UserService userService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.userService = new UserService(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;
        String newPassword = request.getParameter("new_password");

        if (currentUser != null && newPassword != null && !newPassword.trim().isEmpty()) {
            currentUser.setPassword(newPassword);

            boolean success = userService.updateUser(currentUser);

            if (success) {
                if ("ADMIN".equals(currentUser.getRole())) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("memberDashboard.jsp");
                }
            } else {
                request.setAttribute("error", "Échec du changement de mot de passe.");
                request.getRequestDispatcher("/WEB-INF/member/changePassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Le nouveau mot de passe ne peut pas être vide.");
            request.getRequestDispatcher("/WEB-INF/member/changePassword.jsp").forward(request, response);
        }
    }
}