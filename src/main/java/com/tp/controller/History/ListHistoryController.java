package com.tp.controller.History;

import com.tp.dao.DAOFactory;
import com.tp.model.History;
import com.tp.model.User;
import com.tp.service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/listHistory")
public class ListHistoryController extends HttpServlet {

    private HistoryService historyService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.historyService = new HistoryService(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<History> allHistory = historyService.getAllHistory();

        request.setAttribute("historyList", allHistory);
        request.getRequestDispatcher("/WEB-INF/history.jsp").forward(request, response);
    }
}