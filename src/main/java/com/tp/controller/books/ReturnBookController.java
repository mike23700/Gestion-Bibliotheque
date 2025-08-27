package com.tp.controller.books;

import com.tp.model.Loan;
import com.tp.model.User;
import com.tp.service.BookService;
import com.tp.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/returnBook")
public class ReturnBookController extends HttpServlet {

    private final BookService bookService = new BookService();
    private final LoanService loanService = new LoanService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }

        String loanId = request.getParameter("loanId");

        if (loanId != null && !loanId.isEmpty()) {
            try {
                // ✅ récupérer l’emprunt avec son ID
                Loan loanToReturn = loanService.getLoanById(loanId);

                if (loanToReturn != null) {
                    // ✅ mettre à jour la date de retour
                    boolean loanUpdated = loanService.updateLoanReturnDate(loanId, LocalDateTime.now());

                    if (loanUpdated) {
                        // ✅ mettre le livre en disponible
                        boolean bookStatusUpdated = bookService.updateBookStatus(loanToReturn.getBook_id(), "disponible");

                        if (bookStatusUpdated) {
                            session.setAttribute("message", "Le livre a été rendu avec succès.");
                        } else {
                            session.setAttribute("error", "Le retour est enregistré mais le statut du livre n'a pas pu être mis à jour.");
                        }
                    } else {
                        session.setAttribute("error", "Impossible d’enregistrer le retour de l’emprunt.");
                    }
                } else {
                    session.setAttribute("error", "Emprunt introuvable.");
                }
            } catch (Exception e) {
                session.setAttribute("error", "Une erreur est survenue lors du retour du livre.");
                e.printStackTrace();
            }
        } else {
            session.setAttribute("error", "ID d'emprunt invalide ou manquant.");
        }

        response.sendRedirect("memberListLoans");
    }
}
