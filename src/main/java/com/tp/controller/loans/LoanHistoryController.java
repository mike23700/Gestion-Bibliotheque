package com.tp.controller.loans;

import com.tp.model.Loan;
import com.tp.model.User;
import com.tp.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/loanHistory")
public class LoanHistoryController extends HttpServlet {
    LoanService loanService = new LoanService();
    List<Loan> loans = new ArrayList<>();

    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("MEMBER")) {
            response.sendRedirect("login");
            return;
        }

        try {
            loans = loanService.getEveryLoanByUser(currentUser.getUser_id());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("loans", loans);
        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/loans/loanHistory.jsp").forward(request,response);
    }
}
