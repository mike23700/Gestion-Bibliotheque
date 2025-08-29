package com.tp.controller.books;

import com.tp.model.Loan;
import com.tp.model.User;
import com.tp.model.generateID.GenerateIdLoans;
import com.tp.service.BookService;
import com.tp.service.LoanService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Objects;

@WebServlet("/status")
public class UpdateStatusBookController extends HttpServlet {

    BookService bookService = new BookService();
    LoanService loanService = new LoanService();
    GenerateIdLoans G = new GenerateIdLoans();
    Loan loan = new Loan();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;
        assert currentUser != null;

        String bookId = request.getParameter("id_book");
        String action = request.getParameter("action");

        boolean success = false;

        try {
            success = bookService.updateBookStatus(bookId, action);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Données reçues du client:");
        System.out.println("ID du livre: " + bookId);
        System.out.println("Action demandée: " + action);

        if(Objects.equals(action, "emprunter")){

            if(success) {
                loan.setLoan_id(G.generateID());
                loan.setUser_id(currentUser.getUser_id());
                loan.setBook_id(bookId);
                loan.setBorrow_date(LocalDateTime.now());
                loan.setDue_date(LocalDateTime.now().plusDays(14));
                loan.setReturn_date(LocalDateTime.now());

                try {
                    loanService.AddLoan(loan);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                bookService.AddLoanCountOfBook(bookId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("emprunter", "livre Emprunter avec succes");
        }
        if (Objects.equals(action, "reserver")){
            request.setAttribute("reserver", "Livre reserver avec succes");
        }
        if(Objects.equals(action, "rien")){
            request.setAttribute("rien","livre deja reserver");
            response.sendRedirect("listBooks");
            return;
        }




        response.sendRedirect("listBooks");

    }
}
