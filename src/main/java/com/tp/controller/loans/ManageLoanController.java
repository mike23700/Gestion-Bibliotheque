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

@WebServlet("/manageLoan")
public class ManageLoanController extends HttpServlet {
    LoanService loanService = new LoanService();
    List<Loan> loans = new ArrayList<>();

    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session == null ) ? null : (User) session.getAttribute("user");

        if(currentUser == null || currentUser.getRole().equals("ADMIN")){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            loans = loanService.getAllLoans();
            request.setAttribute("loans",loans);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recuperation des reservations");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/ManageLoan.jsp").forward(request,response);
    }
}
