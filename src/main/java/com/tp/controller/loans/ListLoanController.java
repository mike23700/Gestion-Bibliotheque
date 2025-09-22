package com.tp.controller.loans;

import com.tp.model.Loan;
import com.tp.model.Reservation;
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

@WebServlet("/listLoan")
public class ListLoanController extends HttpServlet {
    LoanService loanService = new LoanService();

    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendRedirect("login");
            return;
        }

        List<Loan> loans;

        String searchType = request.getParameter("searchType");
        String searchValue = request.getParameter("searchValue");
        String statusFilter = request.getParameter("status");

        try {
            if (searchValue != null && !searchValue.trim().isEmpty()) {
                switch (searchType) {
                    case "userId":
                        loans = loanService.getEveryLoanByUser(searchValue.trim());
                        break;
                    case "userName":
                        loans = loanService.findByUsername(searchValue.trim());
                        break;
                    case "bookName":
                        loans = loanService.findByBooktitle(searchValue.trim());
                        break;
                    default:
                        loans = loanService.getAllActiveLoans();
                }
            } else if (statusFilter != null && !statusFilter.isEmpty()) {
                loans = loanService.getAllLoans();
            }else {
            loans = loanService.getAllActiveLoans();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("loans", loans);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchValue", searchValue);
        request.setAttribute("status", statusFilter);

        request.setAttribute("activePage", "emprunts");
        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/loans/listLoan.jsp").forward(request,response);
    }
}
