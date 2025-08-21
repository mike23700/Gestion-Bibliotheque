package com.tp.controller.History;

import com.tp.dao.DAOFactory;
import com.tp.model.User;
import com.tp.service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addHistory")
public class AddHistoryController extends HttpServlet {

    private HistoryService historyService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.historyService = new HistoryService(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");
        String actionType = request.getParameter("actionType");
        String actionDescription = request.getParameter("actionDescription");

        boolean success = historyService.addHistory(userId, bookId, actionType, actionDescription);

        if (success) {
            response.sendRedirect("listHistory");
        } else {
            request.setAttribute("error", "Erreur lors de l'ajout a l'historique d'action.");
            request.getRequestDispatcher("/WEB-INF/adminDashboard.jsp").forward(request, response);
        }
    }
}